package com.loofi.notification.processor.services;

import com.loofi.notification.common.entities.Notification;
import com.loofi.notification.processor.dtos.NotificationDTO;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {
  private final NotificationProcessor notificationProcessor;

  public void processAndPublish(NotificationDTO notificationDTO) {
    log.info("Notification request.");
    Notification notification = notificationProcessor.processNotification(notificationDTO);
    log.info("Notification. ID:{}",notification.getId());
    // todo publish message queue with id
    // consumer fetch data from message queue
    // and send notification corresponding channel ( SMS thru telco, email thru email network, Push Notification

  }
}
