package com.loofi.notification.processor.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
  @GetMapping("/home/ping")
  public String home() {
    return "LooFi Notification Processor Service";
  }
}
