package org.hojin.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.IOUtils;



public class S3Service {
	
	@Autowired
	private AmazonS3Client amazonS3Client;
	
	@Value("#{awsConfig['portfolio.aws.secretKey']}")
	private String bucket;
	
	private PutObjectResult upload(String filePath, String uploadKey) throws FileNotFoundException{
		return upload(new FileInputStream(filePath), uploadKey);
	}
	
	private PutObjectResult upload(InputStream inputStream, String uploadKey){
		PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());
		
		PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
		
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return putObjectResult;
	}
	
	public List<PutObjectResult> upload(MultipartFile[] multipartFiles){
		return null;
		
	}
}
