package com.example.awssnstest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.NotificationMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.awssnstest.repository.RepositoryDao;

@RestController
public class MessageController {
	
	@Autowired
	NotificationMessagingTemplate messagingTemplate;
	
	@GetMapping("/")
	public Object list() {
		return RepositoryDao.list();
	}
	
	@PostMapping("/")
	public Object add(@RequestBody String request) {
		System.out.println("Sending request to SNS");
		messagingTemplate.convertAndSend("test1", request);
		return request;
	}

}
