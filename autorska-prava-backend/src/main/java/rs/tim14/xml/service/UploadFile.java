package rs.tim14.xml.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UploadFile {

	public String execute(MultipartFile file, Boolean descriptionFile) {
		try {
			String fileName = getFileName(file, descriptionFile);
			saveFile("./autorska-prava-backend/src/main/resources/a1/" + fileName, file.getBytes());
			return fileName;
		} catch (IOException | NullPointerException exception) {
			System.out.println(exception.getMessage());
			return "";
		}
	}

	public static String getFileName(MultipartFile file, boolean descriptionFile){
		String folderName;
		if (descriptionFile) folderName = "descriptions";
		else folderName = "examples";
		String fileName = file.getOriginalFilename();
		assert fileName != null;
		String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
		return folderName + "/" + (getNextId()) + "." + fileExtension;
	}

	public static long getNextId(){
		return ThreadLocalRandom.current().nextLong(0, Long.MAX_VALUE);
	}

	public static void saveFile(String path, byte[] bytes){
		try {
			File saveFile = new File(path);
			FileOutputStream stream = new FileOutputStream(saveFile);
			stream.write(bytes);
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
