package com.github.pmoerenhout.atcommander.basic.exceptions;

import java.io.IOException;

public class TimeoutException extends IOException {

  private static final long serialVersionUID = -126725439588269898L;

  /**
   * Default constructor.
   */
  public TimeoutException() {
    super("TimeoutException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public TimeoutException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public TimeoutException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public TimeoutException(Throwable cause) {
    super(cause);
  }

}
