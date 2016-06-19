package com.findmycar.util;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.findmycar.contants.GenricConstants;

public class JsonUtil {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JsonUtil.class);

	/**
	 * Instantiates a new json util.
	 */
	private JsonUtil() {
		super();

	}

	/**
	 * This method converts object to json String with passed attribute name
	 *
	 * @param attribute
	 *            json wrapper
	 * @param object
	 *            object
	 * @return the string
	 */
	public static String toJson(final String attribute, final Object object) {
		final String json = toJson(object);
		return "{\"" + attribute + "\":" + json + "}";
	}

	/**
	 * This method converts object to json string
	 *
	 * @param object
	 *            object
	 * @return the string
	 */
	public static String toJson(final Object object) {
		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		final StringWriter sw = new StringWriter();
		try {
			mapper.writeValue(sw, object);
		} catch (IOException e) {
			LOGGER.error("Strig to json failed", e);
		}
		return sw.getBuffer().toString();
	}

	/**
	 * This method converts jsonString to Pojo
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonString
	 *            json string
	 * @param object
	 *            object
	 * @return the t
	 */
	public static <T> T toObject(final String jsonString, final Class object) {

		final ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		T jsonObject = null;
		try {
			jsonObject = (T) mapper.readValue(
					jsonString.getBytes(GenricConstants.UTF8), object);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonObject;
	}

	/**
	 * This method first parses the jsonString strips the attribute name and
	 * then converts to POJO
	 *
	 * @param <T>
	 *            the generic type
	 * @param jsonString
	 *            json string
	 * @param attributeName
	 *            attribute name
	 * @param object
	 *            object
	 * @return the t
	 */
	public static <T> T toObject(final String jsonString,
			final String attributeName, final Class object) {

		final ObjectMapper mapper = new ObjectMapper();
		T jsonObject = null;
		try {
			final JsonNode rootNode = mapper.readTree(jsonString);
			final JsonNode node = rootNode.path(attributeName);
			jsonObject = (T) mapper.readValue(node.toString(), object);
		} catch (IOException e) {
			// #TODO: should we rethrow so that we can have
			// clearer error handling
			LOGGER.error("Json construction exception", e);
		}

		return jsonObject;
	}

}
