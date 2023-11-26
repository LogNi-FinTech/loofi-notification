package com.loofi.notification.common.errors;

public class NotificationRuntimeException extends RuntimeException {
  private String code;

  public NotificationRuntimeException(String code, String message) {
    super(message);
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
