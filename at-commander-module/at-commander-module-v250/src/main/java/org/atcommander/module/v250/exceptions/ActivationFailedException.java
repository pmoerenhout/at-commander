package org.atcommander.module.v250.exceptions;

public class ActivationFailedException extends EquipmentException {

  private static final long serialVersionUID = -227733458188439856L;

  /**
   * Default constructor.
   */
  public ActivationFailedException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public ActivationFailedException(int error, int networkRejectError, String message, Throwable cause) {
    super(error, networkRejectError, message, cause);
  }


  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public ActivationFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public ActivationFailedException(int error, int networkRejectError, String message) {
    super(error, networkRejectError, message);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public ActivationFailedException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public ActivationFailedException(Throwable cause) {
    super(cause);
  }

}
