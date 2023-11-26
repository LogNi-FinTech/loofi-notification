package com.loofi.notification.processor.dtos;

import com.loofi.notification.common.enums.NotificationChannel;
import com.loofi.notification.common.enums.NotificationPriority;

import java.util.List;
import java.util.Map;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationDTO {
  @NotNull
  private String templateName;

  @NotNull
  private NotificationChannel notificationChannel;

  private String locale = "en";

  private String to; // single recipient ( mobile, email, pn id

  private List<String> recipients;

  private Map<String, String> variables;

  private NotificationPriority notificationPriority; // todo implement priority sending
}
