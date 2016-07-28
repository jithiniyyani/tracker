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
import com.stolenvehicle.exception.ExceptionProcessor;
import com.stolenvehicle.service.FileService;
import com.stolenvehicle.service.FindInformationService;
import com.stolenvehicle.util.JsonUtil;

@Controller
public class FindController {

	private static final Logger LOGGER = Logger.getLogger(FindController.class);

	@Autowired
	private FileService fileService;

	@Value("#{properties['base_path']}")
	private String basePath;

	@Autowired
	private FindInformationService findInformationService;

	@RequestMapping(method = RequestMethod.POST, value = "/reportFindForTheft")
	public ResponseEntity<String> reportFind(HttpServletRequest request,
			@RequestBody String jsonRequest) {

		ResponseEntity<String> response = null;
		try {

			FindInformationTo findInformationTo = JsonUtil.toObject(
					jsonRequest, Constants.FIND_INFO, FindInformationTo.class);
			if (StringUtils
					.isEmpty(findInformationTo.getTheft_information_id())) {

				throw new IllegalArgumentException(
						ExceptionConstants.EMPTY_INPUT);

			} else {

				// we expect file upload to complete by this stage
				HttpSession session = request.getSession(false);
				List<AttachmentTo> attachmentList = (List<AttachmentTo>) session
						.getAttribute(Constants.ATTACHMENTS);
				findInformationTo.setAttachments(attachmentList);
				FindInformationTo saveFindInformation = findInformationService
						.saveFindInformation(findInformationTo);

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
			String folder = basePath + "/" + UUID.randomUUID().toString();

			Set<String> keySet = fileMap.keySet();
			for (String fileName : keySet) {

				MultipartFile file = fileMap.get(fileName);
				if (!file.isEmpty()) {
					bytes = file.getBytes();
					fileService.writeToFile(folder, file.getOriginalFilename(),
							bytes);
					attachmentList.add(new AttachmentTo(file
							.getOriginalFilename(), folder,
							AttachmentTypeEnum.FIND));
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
