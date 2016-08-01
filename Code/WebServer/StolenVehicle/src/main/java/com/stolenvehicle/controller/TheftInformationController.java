package com.stolenvehicle.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.stolenvehicle.constants.AttachmentTypeEnum;
import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ErrorEnum;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.constants.FindStatusEnum;
import com.stolenvehicle.constants.TheftStatus;
import com.stolenvehicle.dto.AttachmentTo;
import com.stolenvehicle.dto.ErrorTo;
import com.stolenvehicle.dto.RewardTo;
import com.stolenvehicle.dto.TheftInformationTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.AttachmentService;
import com.stolenvehicle.service.FileService;
import com.stolenvehicle.service.FindInformationService;
import com.stolenvehicle.service.TheftInformationService;
import com.stolenvehicle.service.VehicleService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class TheftInformationController {

	private static final Logger LOGGER = Logger
			.getLogger(TheftInformationController.class);

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private TheftInformationService theftInformationService;

	@Autowired
	private FindInformationService findInformationService;

	@Autowired
	private FileService fileService;

	@Autowired
	private AttachmentService attachmentService;

	@Value("#{properties['base_path']}")
	private String basePath;

	@Value("#{properties['web_server_url']}")
	private String web_server_url;

	@RequestMapping(method = RequestMethod.POST, path = "/registerTheft")
	public ResponseEntity<String> reportTheft(@RequestBody String jsonRequest,
			HttpServletRequest request) {

		ResponseEntity<String> response = null;
		try {

			AppUtil.checkIfUserHasSession(request);
			TheftInformationTo theftInformationTo = JsonUtil
					.toObject(jsonRequest, Constants.THEFT_INFO,
							TheftInformationTo.class);

			vehicleService.saveVehicle(theftInformationTo.getVehicle());
			theftInformationTo.setVehicle_id(theftInformationTo.getVehicle()
					.getId());
			theftInformationService.saveTheftInformation(theftInformationTo);
			List<AttachmentTo> saveAttachments = attachmentService
					.saveAttachments(
							AppUtil.getAttachmentListForTheft(request),
							theftInformationTo.getVehicle_id(), null);
			theftInformationTo.getVehicle().setAttachments(saveAttachments);
			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.THEFT_INFO, theftInformationTo), HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while registering theft request : "
					+ jsonRequest, ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/getTheftInfo")
	public ResponseEntity<String> getTheftInformation(
			@RequestParam(name = "theftId") String theftId) {
		ResponseEntity<String> response = null;
		try {
			TheftInformationTo theftInformation = theftInformationService
					.getTheftInformation(theftId);
			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.THEFT_INFO, theftInformation), HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while fetching theftinformation with id "
					+ theftId, ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;

	}

	@RequestMapping(method = RequestMethod.POST, value = "/reward")
	public ResponseEntity<String> rewardUserForFind(
			@RequestBody String jsonRequest, HttpServletRequest request) {
		ResponseEntity<String> response = null;
		try {

			AppUtil.checkIfUserHasSession(request);
			RewardTo reward = JsonUtil.toObject(jsonRequest, Constants.REWARD,
					RewardTo.class);
			String findId = reward.getFindInformationId();
			boolean updateTheftInformation = theftInformationService
					.updateTheftInformation(reward.getTheftId(),
							reward.getFindInformationId(), TheftStatus.REWARDED);
			boolean updateFindInformationStatus = findInformationService
					.updateFindInformationStatus(findId,
							FindStatusEnum.REWARDED);
			if (updateTheftInformation == true
					&& updateFindInformationStatus == true) {
				response = new ResponseEntity<String>(HttpStatus.OK);
			} else {
				response = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}

		} catch (IllegalArgumentException ex) {

			LOGGER.error("Error while updating theft", ex);
			if (ex.getMessage().equalsIgnoreCase(
					ExceptionConstants.INVALID_SESSION)) {

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.INVALID_SESSION.getCode(),
								ErrorEnum.INVALID_SESSION.getMessage())),
						HttpStatus.FORBIDDEN);

			} else {

				response = new ResponseEntity<String>(JsonUtil.toJson(
						Constants.ERROR,
						new ErrorTo(ErrorEnum.INVALID_JSON.getCode(),
								ErrorEnum.INVALID_JSON.getMessage())),
						HttpStatus.BAD_REQUEST);
			}

		} catch (Exception ex) {

			LOGGER.error("Error while getting theft info with request id "
					+ request, ex);
			response = new ResponseEntity<String>(JsonUtil.toJson(
					Constants.ERROR, new ErrorTo(
							ErrorEnum.INTERNAL_SERVICE_ERROR.getCode(),
							ErrorEnum.INTERNAL_SERVICE_ERROR.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;

	}

	@RequestMapping(value = "/uploadAttachmentsForTheft", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<String> uploadAttachmentsForTheft(
			HttpServletRequest request) {

		ResponseEntity<String> response = null;
		DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = null;
		try {
			if (request instanceof DefaultMultipartHttpServletRequest) {
				defaultMultipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;
			}
			Map<String, MultipartFile> fileMap = defaultMultipartHttpServletRequest
					.getFileMap();
			AppUtil.checkIfUserHasSession(request);
			HttpSession session = request.getSession(false);
			List<AttachmentTo> attachmentList = null;
			Object attribute = session.getAttribute(Constants.ATTACHMENTS);
			if (attribute == null) {
				attachmentList = new ArrayList<>();
				session.setAttribute(Constants.ATTACHMENTS, attachmentList);
			} else {
				attachmentList = (ArrayList<AttachmentTo>) attribute;
			}
			UserTo user = AppUtil.getUserFromSession(request);
			byte[] bytes;

			String baseFolderForUser = basePath + "/" + user.getId();
			String tnxId = UUID.randomUUID().toString();
			String folder = baseFolderForUser + "/" + tnxId;

			Set<String> keySet = fileMap.keySet();
			for (String fileName : keySet) {

				MultipartFile file = fileMap.get(fileName);
				if (!file.isEmpty()) {
					bytes = file.getBytes();
					fileName = file.getOriginalFilename();
					fileService.writeToFile(folder, fileName, bytes);
					attachmentList.add(new AttachmentTo(file
							.getOriginalFilename(), folder, web_server_url
							+ user.getId() + "/" + tnxId + "/" + fileName,
							AttachmentTypeEnum.THEFT));
				}
			}

			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while saving attachments ", ex);
			response = ExceptionProcessor.handleException(ex);

		} finally {

		}
		return response;
	}

	@RequestMapping(value = "/uploadAttachmentsForAnonymousTheft", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<String> uploadAttachmentsForAnonTheft(
			HttpServletRequest request) {

		ResponseEntity<String> response = null;
		DefaultMultipartHttpServletRequest defaultMultipartHttpServletRequest = null;
		try {
			if (request instanceof DefaultMultipartHttpServletRequest) {
				defaultMultipartHttpServletRequest = (DefaultMultipartHttpServletRequest) request;
			}
			Map<String, MultipartFile> fileMap = defaultMultipartHttpServletRequest
					.getFileMap();
			HttpSession session = request.getSession();
			List<AttachmentTo> attachmentList = null;
			Object attribute = session.getAttribute(Constants.ATTACHMENTS);
			if (attribute == null) {
				attachmentList = new ArrayList<>();
				session.setAttribute(Constants.ATTACHMENTS, attachmentList);
			} else {
				attachmentList = (ArrayList<AttachmentTo>) attribute;
			}
			UserTo user = AppUtil.getUserFromSession(request);
			byte[] bytes;
			String tnxId = UUID.randomUUID().toString();
			String folder = basePath + "/" + tnxId;

			Set<String> keySet = fileMap.keySet();
			for (String fileName : keySet) {

				MultipartFile file = fileMap.get(fileName);
				if (!file.isEmpty()) {
					bytes = file.getBytes();
					fileName = file.getOriginalFilename();
					fileService.writeToFile(folder, fileName, bytes);
					attachmentList.add(new AttachmentTo(file
							.getOriginalFilename(), folder, web_server_url
							+ tnxId + "/" + fileName, AttachmentTypeEnum.FIND));
				}
			}

			response = new ResponseEntity<String>(HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while saving attachments ", ex);
			response = ExceptionProcessor.handleException(ex);

		} finally {

		}
		return response;
	}
}
