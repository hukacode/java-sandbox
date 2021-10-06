/*
 * Copyright 2021 hukacode
 */
package com.hukacode.sandbox.springboot.oauth2.sociallogin.exception;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {

  /** */
  private static final long serialVersionUID = 5570981880007077317L;

  public UserAlreadyExistAuthenticationException(final String msg) {
    super(msg);
  }
}
