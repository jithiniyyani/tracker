package com.findmycar.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.findmycar.service.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public byte[] getBinaryFile(String filePath) {

		byte[] fileBytes = null;
		try {
			final Path path = Paths.get(filePath);
			fileBytes = Files.readAllBytes(path);

		} catch (InvalidPathException | IOException ex) {

			
		}
		return fileBytes;
	}

	@Override
	public boolean writeToFile(String folderPath, String fileName, byte[] data) {

		boolean status = false;
		final File file = createFile(folderPath, fileName);
		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(data);
			status = true;
		} catch (IOException ex) {

			ex.printStackTrace();

		}
		return status;
	}

	private File createFile(final String folderPath, final String filename) {

		File file = null;
		final File folder = new File(folderPath);

		synchronized (this) {
			if (!folder.exists()) {

				folder.mkdirs();
			}

		}
		file = new File(folderPath + File.separator + filename);
		return file;
	}
}
