package com.loofi.notification.processor.services;

import com.loofi.notification.common.entities.Notification;
import com.loofi.notification.common.entities.NotificationTemplate;
import com.loofi.notification.common.enums.DeliveryStatus;
import com.loofi.notification.common.errors.NotificationErrorCode;
import com.loofi.notification.common.errors.NotificationRuntimeException;
import com.loofi.notification.common.repositories.NotificationRepository;
import com.loofi.notification.processor.dtos.NotificationDTO;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringReader;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class NotificationProcessor {

  private final TemplateService templateService;
  private final Configuration freeMarkerConfiguration;
  private final NotificationRepository notificationRepository;

  @Transactional
  public Notification processNotification(NotificationDTO notificationDTO) {
    NotificationTemplate notificationTemplate = templateService.get(notificationDTO.getTemplateName(), notificationDTO.getNotificationChannel(), notificationDTO.getLocale());
    String notificationText;
    try {
      Template freeMakerTemplate = new Template(notificationTemplate.getTemplateName(), new StringReader(notificationTemplate.getTemplate()),
        freeMarkerConfiguration);
      notificationText = FreeMarkerTemplateUtils.processTemplateIntoString(
        freeMakerTemplate, notificationDTO.getVariables());

    } catch (IOException | TemplateException ex) {
      throw new NotificationRuntimeException(NotificationErrorCode.INTERNAL_ERROR, ex.getMessage());
    }
    Notification notification = new Notification();
    notification.setNotificationChannel(notificationDTO.getNotificationChannel());
    notification.setBody(notificationText);
    notification.setNotificationTemplate(notificationTemplate);
    notification.setDeliveryStatus(DeliveryStatus.PENDING);
    notification.setPriority(notificationDTO.getNotificationPriority());
    return notificationRepository.save(notification);
  }

}
