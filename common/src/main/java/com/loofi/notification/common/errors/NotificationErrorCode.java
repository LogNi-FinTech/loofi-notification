package com.loofi.notification.common.errors;

import java.util.HashMap;
import java.util.Map;

public class NotificationErrorCode {
  public static final String TEMPLATE_NOT_FOUND = "4004";
  public static final String INVALID_INPUT = "4000";
  public static final String INVALID_MFA_REQUEST = "4002";
  public static final String INTERNAL_ERROR = "5000";


  // Error mapping
  public static final Map<String, String> ERROR_MAP = new HashMap<String, String>();

  static {
    ERROR_MAP.put(TEMPLATE_NOT_FOUND, "Template not found Template : %s");
    ERROR_MAP.put(INVALID_MFA_REQUEST, "MFA already exist");
  }

}
