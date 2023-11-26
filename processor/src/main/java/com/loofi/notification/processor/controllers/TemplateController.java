package com.loofi.notification.processor.controllers;


import com.loofi.notification.common.entities.NotificationTemplate;
import com.loofi.notification.common.repositories.NotificationTemplateRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TemplateController {

  private final NotificationTemplateRepository notificationTemplateRepository;

  @PostMapping("/v1/template")
  public NotificationTemplate createNotificationTemplate(@RequestBody @Valid NotificationTemplate notificationTemplate) {
    return notificationTemplateRepository.save(notificationTemplate);
  }

  @GetMapping("/v1/template")
  public Page<NotificationTemplate> createNotificationTemplate(Pageable pageable) {
    return notificationTemplateRepository.findAll(pageable);
  }
}
