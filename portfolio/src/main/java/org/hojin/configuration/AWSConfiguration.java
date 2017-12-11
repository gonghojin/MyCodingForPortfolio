package org.hojin.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;

@Configuration
//@PropertySource(value = {"classpath:/config/awsConfig.properties"})
public class AWSConfiguration {
	
	@Value("${portfolio.aws.accessKey}")
	private String accessKey;
	
	@Value("${portfolio.aws.secretKey}")
	private String secretKey;
	
	@Value("${portfolio.aws.region}")
	private String region;
	
	@Bean
	public BasicAWSCredentials basicAWSCredentials(){
		return new BasicAWSCredentials(accessKey, secretKey);
	}
	
	@Bean
	public AmazonS3Client amazonS3Client(AWSCredentials awsCredentials){
		AmazonS3Client amazonS3Client = new AmazonS3Client(awsCredentials);
		amazonS3Client.setRegion(Region.getRegion(Regions.fromName(region)));
		return amazonS3Client;
	}
}
