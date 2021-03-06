package com.github.pmoerenhout.atcommander.module.v250.exceptions;

public class NoCarrierException extends DialException {

  private static final long serialVersionUID = -19842598396110598L;

  /**
   * Default constructor.
   */
  public NoCarrierException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public NoCarrierException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public NoCarrierException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public NoCarrierException(Throwable cause) {
    super(cause);
  }

}
