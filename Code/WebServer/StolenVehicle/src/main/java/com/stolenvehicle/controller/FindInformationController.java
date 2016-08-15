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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

import com.stolenvehicle.constants.AttachmentTypeEnum;
import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.constants.ExceptionConstants;
import com.stolenvehicle.dto.AttachmentTo;
import com.stolenvehicle.dto.FindInformationTo;
import com.stolenvehicle.dto.UserTo;
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.FileService;
import com.stolenvehicle.service.FindInformationService;
import com.stolenvehicle.service.NotificationService;
import com.stolenvehicle.util.AppUtil;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class FindInformationController {

	private static final Logger LOGGER = Logger
			.getLogger(FindInformationController.class);

	@Autowired
	private FileService fileService;

	@Value("#{properties['base_path']}")
	private String basePath;

	@Autowired
	private FindInformationService findInformationService;

	@Autowired
	private NotificationService notificationService;

	@Value("#{properties['web_server_url']}")
	private String web_server_url;

	@RequestMapping(method = RequestMethod.GET, value = "findInformationForUser")
	public ResponseEntity<String> getFindInformationForUser(
			HttpServletRequest request) {
		ResponseEntity<String> response = null;
		try {

			AppUtil.checkIfUserHasSession(request);
			UserTo user = AppUtil.getUserFromSession(request);
			List<FindInformationTo> findInformationListForUser = findInformationService
					.getFindInformationListForUser(user.getId());
			response = new ResponseEntity<>(JsonUtil.toJson(
					Constants.FIND_INFO_LIST, findInformationListForUser),
					HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while fetching find info list ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "findInformationListReadyForReward")
	public ResponseEntity<String> findInformationListReadyForReward(
			HttpServletRequest request) {
		ResponseEntity<String> response = null;
		try {

			AppUtil.checkIfUserHasSession(request);
			UserTo user = AppUtil.getUserFromSession(request);
			List<FindInformationTo> findInformationListForUser = findInformationService
					.findInformationListReadyForReward(user.getId());
			response = new ResponseEntity<>(JsonUtil.toJson(
					Constants.FIND_INFO_LIST, findInformationListForUser),
					HttpStatus.OK);
		} catch (Exception ex) {

			LOGGER.error("Error while fetching find info list ", ex);
			response = ExceptionProcessor.handleException(ex);
		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "updateFindInformationStatus")
	public ResponseEntity<String> updateFindInformationStatus(
			HttpServletRequest request, @RequestBody String jsonRequest) {
		ResponseEntity<String> response = null;
		try {

			FindInformationTo findInformationTo = JsonUtil.toObject(
					jsonRequest, Constants.FIND_INFO, FindInformationTo.class);
			boolean updateFindInformationStatus = findInformationService
					.updateFindInformationStatus(findInformationTo.getId(),
							findInformationTo.getFindStatus());
			response = updateFindInformationStatus ? new ResponseEntity<String>(
					HttpStatus.OK) : new ResponseEntity<String>(
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {

			response = ExceptionProcessor.handleException(ex);

		} finally {

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/reportFindForTheft")
	public ResponseEntity<String> reportFind(HttpServletRequest request,
			@RequestBody String jsonRequest) {

		ResponseEntity<String> response = null;
		try {

			FindInformationTo findInformationTo = JsonUtil.toObject(
					jsonRequest, Constants.FIND_INFO, FindInformationTo.class);
			if (StringUtils
					.isEmpty(findInformationTo.getTheft_information_id())
					|| StringUtils.isEmpty(findInformationTo.getVehicle_id())
					|| StringUtils.isEmpty(findInformationTo.getUser_id())) {

				throw new IllegalArgumentException(
						ExceptionConstants.EMPTY_INPUT);

			} else {

				// we expect file upload to complete by this stage
				// #TODO: handle transaction here
				HttpSession session = request.getSession(false);
				List<AttachmentTo> attachmentList = (List<AttachmentTo>) session
						.getAttribute(Constants.ATTACHMENTS);
				findInformationTo.setAttachments(attachmentList);
				FindInformationTo saveFindInformation = findInformationService
						.saveFindInformation(findInformationTo);
				notificationService.sendFindNotification(findInformationTo
						.getUser_id());
				response = new ResponseEntity<String>(HttpStatus.OK);
			}

		} catch (Exception ex) {

			LOGGER.error("Error while saving find", ex);
			response = ExceptionProcessor.handleException(ex);

		} finally {

		}
		return response;
	}

	@RequestMapping(value = "/uploadAttachmentsForFind", headers = ("content-type=multipart/*"), method = RequestMethod.POST)
	public ResponseEntity<String> uploadCarAttachments(
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

	@RequestMapping(method = RequestMethod.POST, value = "/reportAnonymousFind")
	public ResponseEntity<String> reportAnonymousFind(
			HttpServletRequest request, @RequestBody String jsonRequest) {

		ResponseEntity<String> response = null;
		try {

			FindInformationTo findInformationTo = JsonUtil.toObject(
					jsonRequest, Constants.FIND_INFO, FindInformationTo.class);
			// we expect file upload to complete by this stage
			// #TODO: handle transaction here
			HttpSession session = request.getSession(false);
			List<AttachmentTo> attachmentList = (List<AttachmentTo>) session
					.getAttribute(Constants.ATTACHMENTS);
			findInformationTo.setAttachments(attachmentList);
			FindInformationTo saveFindInformation = findInformationService
					.saveFindInformation(findInformationTo);
			response = new ResponseEntity<String>(HttpStatus.OK);

		} catch (Exception ex) {

			LOGGER.error("Error while saving find", ex);
			response = ExceptionProcessor.handleException(ex);

		} finally {

		}
		return response;
	}

}
