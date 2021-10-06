/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {
  /** */
  private static final long serialVersionUID = 752858877580882829L;

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }
}
