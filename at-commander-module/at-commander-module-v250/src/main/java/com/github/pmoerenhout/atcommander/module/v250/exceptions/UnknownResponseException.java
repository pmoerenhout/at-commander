package com.github.pmoerenhout.atcommander.module.v250.exceptions;

import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;

public class UnknownResponseException extends ResponseException {


  private static final long serialVersionUID = 5415301023762892544L;

  /**
   * Default constructor.
   */
  public UnknownResponseException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public UnknownResponseException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public UnknownResponseException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public UnknownResponseException(Throwable cause) {
    super(cause);
  }

}
