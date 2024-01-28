package com.loofi.notification.processor.services;

import com.loofi.notification.common.entities.Notification;
import com.loofi.notification.processor.dtos.NotificationDTO;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

  @Value("${rabbitmq.exchange.name}")
  private String exchange;
  @Value("${rabbitmq.routing.key}")
  private String routingKey;

  private final NotificationProcessor notificationProcessor;
  private final RabbitTemplate rabbitTemplate;

  public void processAndPublish(NotificationDTO notificationDTO) {
    log.info("Notification request.");
    Notification notification = notificationProcessor.processNotification(notificationDTO);
    log.info("Notification. ID:{}",notification.getId());
    rabbitTemplate.convertAndSend(exchange,routingKey, notification.getId().toString());
  }
}
