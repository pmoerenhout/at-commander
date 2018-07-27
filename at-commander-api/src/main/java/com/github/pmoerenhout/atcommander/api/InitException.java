package com.github.pmoerenhout.atcommander.api;

import java.io.IOException;

public class InitException extends IOException {

  private static final long serialVersionUID = -5721916940450131618L;

  /**
   * Default constructor.
   */
  public InitException() {
    super("InitException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public InitException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public InitException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public InitException(Throwable cause) {
    super(cause);
  }

}
