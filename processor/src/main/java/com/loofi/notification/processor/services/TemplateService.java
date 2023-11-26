package com.loofi.notification.processor.services;

import com.loofi.notification.common.entities.NotificationTemplate;
import com.loofi.notification.common.enums.NotificationChannel;
import com.loofi.notification.common.errors.NotificationBusinessException;
import com.loofi.notification.common.errors.NotificationErrorCode;
import com.loofi.notification.common.repositories.NotificationTemplateRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.loofi.notification.common.errors.NotificationErrorCode.TEMPLATE_NOT_FOUND;

@RequiredArgsConstructor
@Slf4j
@Service
public class TemplateService {

  private final NotificationTemplateRepository notificationTemplateRepository;

  public NotificationTemplate get(String templateName, NotificationChannel notificationChannel, String locale) {
    return notificationTemplateRepository.findNotificationTemplateByTemplateNameAndNotificationChannelAndLocale(templateName, notificationChannel, locale)
      .orElseThrow(() -> new NotificationBusinessException(TEMPLATE_NOT_FOUND, String.format(NotificationErrorCode.ERROR_MAP.get(TEMPLATE_NOT_FOUND), templateName)));
  }
}
