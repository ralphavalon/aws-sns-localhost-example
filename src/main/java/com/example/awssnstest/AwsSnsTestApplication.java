package com.example.awssnstest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.context.annotation.Bean;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@SpringBootApplication
public class AwsSnsTestApplication {

	final AWSCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials("foo", "bar"));

	public static void main(String[] args) {
		SpringApplication.run(AwsSnsTestApplication.class, args);
	}
	
	@Bean
	public AmazonSNS amazonSNS() {
		return AmazonSNSClientBuilder.standard()
        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://sns:9911", "us-east-1"))
        .withCredentials(credentialsProvider)
        .build();
	}
	
	@Bean
	public NotificationMessagingTemplate notificationMessagingTemplate(
	  AmazonSNS amazonSNS) {
	    return new NotificationMessagingTemplate(amazonSNS);
	}

}
