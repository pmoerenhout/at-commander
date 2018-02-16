package org.atcommander.module.v250.exceptions;

import org.atcommander.basic.exceptions.ResponseException;

public class NoFreeSocketException extends ResponseException {

  private static final long serialVersionUID = -116349472588264326L;

  /**
   * Default constructor.
   */
  public NoFreeSocketException() {
    super("NoFreeSocketException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public NoFreeSocketException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public NoFreeSocketException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public NoFreeSocketException(Throwable cause) {
    super(cause);
  }

}
