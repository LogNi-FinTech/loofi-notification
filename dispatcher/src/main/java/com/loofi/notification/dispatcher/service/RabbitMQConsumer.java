package com.loofi.notification.dispatcher.service;

import com.loofi.notification.common.entities.Notification;
import com.loofi.notification.common.errors.NotificationBusinessException;
import com.loofi.notification.common.errors.NotificationErrorCode;
import com.loofi.notification.common.repositories.NotificationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RabbitMQConsumer {


  private final NotificationRepository notificationRepository;
  private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

  @RabbitListener(queues = {"${rabbitmq.queue.name}"})
  public void sendNotification(String id){
    logger.info(String.format("Message id %s", id));
    Notification notification = notificationRepository.findById(Long.valueOf(id)).orElseThrow(
      () -> new NotificationBusinessException(NotificationErrorCode.TEMPLATE_NOT_FOUND, "Template Not found"));
    logger.info(String.format("Notification received -> %s", notification.getDeliveryStatus().toString()));
    //todo: will write code to send email
  }
}
