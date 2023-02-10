package com.yuulab.exception;

import org.springframework.http.converter.HttpMessageConversionException;

public class HttpMessageParseException extends HttpMessageConversionException {

  public HttpMessageParseException(String message) {
    super(message);
  }

  public HttpMessageParseException(String message, Throwable cause) {
    super(message, cause);
  }
}
