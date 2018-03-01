package com.github.pmoerenhout.atcommander.module.v250.exceptions;

public class GprsAttachFailedException extends EquipmentException {


  private static final long serialVersionUID = -5151495668176699262L;

  /**
   * Default constructor.
   */
  public GprsAttachFailedException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public GprsAttachFailedException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param error              is the error.
   * @param networkRejectError is the error.
   * @param message            is the detail message.
   */
  public GprsAttachFailedException(int error, int networkRejectError, String message) {
    super(error, networkRejectError, message);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public GprsAttachFailedException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public GprsAttachFailedException(Throwable cause) {
    super(cause);
  }

  /**
   * Construct with specified message.
   *
   * @param error              is the error.
   * @param networkRejectError is the error.
   * @param message            is the detail message.
   * @param cause              is the parent cause.
   */
  public GprsAttachFailedException(final int error, final int networkRejectError, final String message, final Throwable cause) {
    super(error, networkRejectError, message, cause);
  }
}
