package com.findmycar.service;

public interface FileService {

	public byte[] getBinaryFile(String filePath);

	public boolean writeToFile(String folderPath, String fileName, byte[] data);
}
