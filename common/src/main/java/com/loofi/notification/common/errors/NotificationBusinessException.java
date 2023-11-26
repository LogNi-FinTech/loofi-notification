package com.loofi.notification.common.errors;

public class NotificationBusinessException extends RuntimeException {
  private String code;

  public NotificationBusinessException(String code, String message) {
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
