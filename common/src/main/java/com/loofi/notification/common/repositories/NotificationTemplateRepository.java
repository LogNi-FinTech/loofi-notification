package com.loofi.notification.common.repositories;

import com.loofi.notification.common.entities.NotificationTemplate;
import com.loofi.notification.common.enums.NotificationChannel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, Long> {
  Optional<NotificationTemplate> findNotificationTemplateByTemplateNameAndNotificationChannelAndLocale(String templateName, NotificationChannel channel, String locale);
}
