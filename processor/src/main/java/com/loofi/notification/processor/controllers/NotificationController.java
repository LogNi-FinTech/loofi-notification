package com.loofi.notification.processor.controllers;

import com.loofi.notification.processor.dtos.NotificationDTO;
import com.loofi.notification.processor.services.NotificationService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NotificationController {

  private final NotificationService notificationService;

  // todo we can make it async also
  @PostMapping("/v1/send")
  public void send(@Valid @RequestBody NotificationDTO notificationDTO) {
    notificationService.processAndPublish(notificationDTO);
  }
}
