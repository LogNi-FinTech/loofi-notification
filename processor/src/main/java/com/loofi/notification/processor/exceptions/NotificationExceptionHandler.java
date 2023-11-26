package com.loofi.notification.processor.exceptions;

import com.loofi.notification.common.errors.ErrorResponse;
import com.loofi.notification.common.errors.NotificationBusinessException;
import com.loofi.notification.common.errors.NotificationErrorCode;
import com.loofi.notification.common.errors.NotificationRuntimeException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import static com.loofi.notification.common.errors.NotificationErrorCode.INTERNAL_ERROR;
import static com.loofi.notification.common.errors.NotificationErrorCode.INVALID_INPUT;

@Slf4j
@ControllerAdvice
public class NotificationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public @ResponseBody ErrorResponse handleValidationExceptions(
    MethodArgumentNotValidException ex, WebRequest webRequest) {
    Map<String, String> errors = new HashMap<String, String>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ErrorResponse.builder().code(INVALID_INPUT)
      .message(NotificationErrorCode.ERROR_MAP.get(INVALID_INPUT))
      .build();

  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({NotificationBusinessException.class})
  public @ResponseBody ErrorResponse handleBusinessError(NotificationBusinessException ex) {
    return ErrorResponse.builder().code(ex.getCode())
      .message(ex.getMessage())
      .build();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class, RuntimeException.class, NotificationRuntimeException.class})
  public @ResponseBody ErrorResponse handleAllExceptions(Exception ex) {
    return ErrorResponse.builder().code(INTERNAL_ERROR)
      .message(ex.getMessage())
      .build();
  }
}
