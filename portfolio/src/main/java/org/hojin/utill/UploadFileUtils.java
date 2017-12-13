package org.hojin.utill;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) {
		// 랜덤 값으로 파일명의 중복을 제거함
		String uid = UUID.randomUUID().toString();

		String savedName = uid + "_" + originalName;
		String savedPath = calcPath(uploadPath);

		File target = new File(uploadPath + savedPath, savedName);
		try {
			FileCopyUtils.copy(fileData, target);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadedFileName = null;
		if (MediaUtils.getMediaMap(formatName) != null) {// 이미지 파일 형태
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else{
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		
		return uploadedFileName;
	}

	// 파일명을 날짜로 구분
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		logger.info(datePath);
		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}

		for (String path : paths) {
			File dirPath = new File(uploadPath + path);

			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}
	
	//썸네일 파일 만들기
	private static String makeThumbnail(String uploadPath, String path, String fileName) {

		BufferedImage sourceImg;
		String thumbnailName = null;
		try {
			sourceImg = ImageIO.read(new File(uploadPath + path, fileName));

			BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

			thumbnailName = uploadPath + path + File.separator + "s_" + fileName;

			File newFile = new File(thumbnailName);
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			ImageIO.write(destImg, formatName.toUpperCase(), newFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
		// 윈도우의 경로일 경우 '\'를 사용하는데, 브라우저에서는 인식하지 않음 따라서 '/' 치환
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	//이미지 파일 외 경로 치환용도로만 사용
	private static String makeIcon(String uploadPath, String savedPath, String savedName){
		
		String iconName = uploadPath + savedPath + savedName;
		
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
