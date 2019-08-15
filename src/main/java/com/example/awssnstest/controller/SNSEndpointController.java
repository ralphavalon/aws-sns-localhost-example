package com.example.awssnstest.controller;

import org.springframework.cloud.aws.messaging.config.annotation.NotificationMessage;
import org.springframework.cloud.aws.messaging.config.annotation.NotificationSubject;
import org.springframework.cloud.aws.messaging.endpoint.NotificationStatus;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationMessageMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationSubscriptionMapping;
import org.springframework.cloud.aws.messaging.endpoint.annotation.NotificationUnsubscribeConfirmationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.awssnstest.repository.RepositoryDao;

@Controller
@RequestMapping("/topic-subscriber")
public class SNSEndpointController {
 
    @NotificationSubscriptionMapping
    public void confirmUnsubscribeMessage(
      NotificationStatus notificationStatus) {
    	System.out.println("Confirming unsubscription");
        notificationStatus.confirmSubscription();
    }
  
    @NotificationMessageMapping
    public void receiveNotification(@NotificationMessage String message, 
      @NotificationSubject String subject) {
    	System.out.println("Received message");
        RepositoryDao.add(message);
    }
 
    @NotificationUnsubscribeConfirmationMapping
    public void confirmSubscriptionMessage(
      NotificationStatus notificationStatus) {
    	System.out.println("Confirming subscription");
        notificationStatus.confirmSubscription();
    }
}
