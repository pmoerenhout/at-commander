package com.github.pmoerenhout.atcommander.module.v250.exceptions;

import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;

public abstract class DialException extends ResponseException {

  public DialException() {
  }

  public DialException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DialException(final String message) {
    super(message);
  }

  public DialException(final Throwable cause) {
    super(cause);
  }
}
