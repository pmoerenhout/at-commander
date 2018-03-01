package com.github.pmoerenhout.atcommander.module.v250.exceptions;

import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;

public class MessageServiceErrorException extends ResponseException {

  private static final long serialVersionUID = -1984234843472L;

  /**
   * Default constructor.
   */
  public MessageServiceErrorException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public MessageServiceErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public MessageServiceErrorException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public MessageServiceErrorException(Throwable cause) {
    super(cause);
  }

}
