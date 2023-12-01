package com.loofi.notification.processor.dtos.mfa;


import com.loofi.notification.common.enums.NotificationChannel;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MfaRequest {

  @NotNull
  private NotificationChannel mfaChannel;

  private String userIdentifier;

  private String email;

  private String phoneNumber;

  private String smsTemplateName ;

  private String emailTemplateName ;

  @NotNull private Locale locale = Locale.ENGLISH;

  private Map<String, String> variables = new HashMap<>();
}
