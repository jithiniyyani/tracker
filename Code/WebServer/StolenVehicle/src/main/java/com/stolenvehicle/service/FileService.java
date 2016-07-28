package com.stolenvehicle.service;

import com.stolenvehicle.exception.BusinessException;

public interface FileService {

	public byte[] getBinaryFile(String filePath);

	public boolean writeToFile(String folderPath, String fileName, byte[] data) throws BusinessException;
}
