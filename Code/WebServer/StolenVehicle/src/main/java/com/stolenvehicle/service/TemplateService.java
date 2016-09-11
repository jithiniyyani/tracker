package com.stolenvehicle.service;

import java.util.List;
import java.util.ResourceBundle;

public interface TemplateService {

	public String generateContent(String vmFile, String name, Object object, ResourceBundle resource);

	public String generateContent(String vmFile, ResourceBundle resource, List<String> nameList, Object... object);

}
