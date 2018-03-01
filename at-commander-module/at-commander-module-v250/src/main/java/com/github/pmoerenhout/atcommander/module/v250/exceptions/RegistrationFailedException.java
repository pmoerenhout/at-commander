package com.github.pmoerenhout.atcommander.module.v250.exceptions;

public class RegistrationFailedException extends EquipmentException {

  private static final long serialVersionUID = -2075761022117894075L;

  /**
   * Default constructor.
   */
  public RegistrationFailedException() {
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
  public RegistrationFailedException(int error, int networkRejectError, String message, Throwable cause) {
    super(error, networkRejectError, message, cause);
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public RegistrationFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param error              is the error.
   * @param networkRejectError is the error.
   * @param message            is the detail message.
   */
  public RegistrationFailedException(int error, int networkRejectError, String message) {
    super(error, networkRejectError, message);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public RegistrationFailedException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public RegistrationFailedException(Throwable cause) {
    super(cause);
  }

}
