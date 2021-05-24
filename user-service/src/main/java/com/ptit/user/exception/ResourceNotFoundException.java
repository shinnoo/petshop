package com.ptit.user.exception;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
@ComponentScan("com.ptit.user")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

  /**
   * Instantiates a new Resource not found exception.
   *
   * @param message the message
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}