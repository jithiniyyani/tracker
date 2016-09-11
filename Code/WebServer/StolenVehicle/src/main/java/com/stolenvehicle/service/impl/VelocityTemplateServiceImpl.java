package com.stolenvehicle.service.impl;

import java.io.StringWriter;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stolenvehicle.constants.Constants;
import com.stolenvehicle.exception.SystemException;
import com.stolenvehicle.service.TemplateService;

@Service
public class VelocityTemplateServiceImpl implements TemplateService {

	@Autowired
	private VelocityEngine velocityEngine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ec.darwin.common.services.facade.iface.VelocityTemplateService#
	 * generateContent(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public String generateContent(final String vmFile, final String name, final Object object,
			final ResourceBundle resourceBundle) {

		final StringWriter writer = new StringWriter();
		VelocityContext context = null;
		Template template = null;
		try {
			template = velocityEngine.getTemplate(vmFile, Constants.UTF8);
			context = new VelocityContext();
			context.put(name, object);
			context.put(Constants.RESOURCE_BUNDLE, resourceBundle);
			template.merge(context, writer);
		} catch (ResourceNotFoundException | MethodInvocationException | ParseErrorException ex) {
			throw new SystemException("Error while generating template", ex);

		} finally {
			template = null;
			context = null;
		}
		return writer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ec.darwin.common.services.facade.iface.TemplateService#
	 * generateContent (java.lang.String, java.util.ResourceBundle,
	 * java.util.List, java.lang.Object[])
	 */
	@Override
	public String generateContent(String vmFile, ResourceBundle resourceBundle, List<String> nameList,
			Object... object) {

		final StringWriter writer = new StringWriter();
		VelocityContext context = null;
		Template template = null;
		int i = 0;
		try {
			template = velocityEngine.getTemplate(vmFile, Constants.UTF8);
			context = new VelocityContext();
			for (String name : nameList) {

				context.put(name, object[i++]);
			}
			context.put(Constants.RESOURCE_BUNDLE, resourceBundle);
			template.merge(context, writer);
		} catch (ResourceNotFoundException | MethodInvocationException | ParseErrorException ex) {
			throw new SystemException("Error while generating template", ex);

		} finally {
			template = null;
			context = null;
		}
		return writer.toString();
	}

}