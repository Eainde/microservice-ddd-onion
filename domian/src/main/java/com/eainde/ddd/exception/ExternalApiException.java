package com.eainde.ddd.exception;

public class ExternalApiException extends RuntimeException {
  public ExternalApiException(final String message) {
    super(message);
  }

  public ExternalApiException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
