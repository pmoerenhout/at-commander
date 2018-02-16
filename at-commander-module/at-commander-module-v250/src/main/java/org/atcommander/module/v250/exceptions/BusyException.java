package org.atcommander.module.v250.exceptions;

import org.atcommander.basic.exceptions.ResponseException;

public class BusyException extends ResponseException {

  private static final long serialVersionUID = -114525498788234598L;

  /**
   * Default constructor.
   */
  public BusyException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public BusyException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public BusyException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public BusyException(Throwable cause) {
    super(cause);
  }

}
