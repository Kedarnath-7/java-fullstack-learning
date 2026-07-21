package com.northernarc.codingassessment6.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementWithSameIDAlreadyExistsException extends RuntimeException {

  public ElementWithSameIDAlreadyExistsException(String message) {
    super(message);
  }
}
