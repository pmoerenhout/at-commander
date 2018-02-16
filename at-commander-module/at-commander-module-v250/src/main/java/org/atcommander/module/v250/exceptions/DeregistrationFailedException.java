package org.atcommander.module.v250.exceptions;

public class DeregistrationFailedException extends EquipmentException {

  private static final long serialVersionUID = 2537574248241850327L;

  /**
   * Default constructor.
   */
  public DeregistrationFailedException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param error              is the error.
   * @param networkRejectError is the error.
   * @param message            is the detail message.
   * @param cause              is the parent cause.
   */
  public DeregistrationFailedException(int error, int networkRejectError, String message, Throwable cause) {
    super(error, networkRejectError, message, cause);
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public DeregistrationFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param error              is the error.
   * @param networkRejectError is the error.
   * @param message            is the detail message.
   */
  public DeregistrationFailedException(int error, int networkRejectError, String message) {
    super(error, networkRejectError, message);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public DeregistrationFailedException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public DeregistrationFailedException(Throwable cause) {
    super(cause);
  }

}
