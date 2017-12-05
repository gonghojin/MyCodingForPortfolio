package org.hojin.controller;

import org.hojin.service.S3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/aws/s3")
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private S3Service s3Service;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile[] multipartFiles){
		logger.info("컨트롤러는 찍힙니다.!!!");
		logger.info(multipartFiles[0].getOriginalFilename());
		//지금 UI는 MultipartFile이 배열값이 아니지만, 다중 파일로 들어올 경우 stream filter 사용법을 배우기 위해, service는 배열로 잡음
		for(MultipartFile loggerFile: multipartFiles){
			logger.info("originalName: " + loggerFile.getOriginalFilename() );
		}
		
		s3Service.upload(multipartFiles);
		return new ResponseEntity<String>(multipartFiles[0].getOriginalFilename(), HttpStatus.CREATED);
	}
	
}
