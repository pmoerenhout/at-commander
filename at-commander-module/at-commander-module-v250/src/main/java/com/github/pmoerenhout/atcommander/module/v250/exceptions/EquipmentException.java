package com.github.pmoerenhout.atcommander.module.v250.exceptions;

import com.github.pmoerenhout.atcommander.ModemUtil;

public class EquipmentException extends Exception {

  private static final long serialVersionUID = 4085216406554924825L;

  private int error;
  private int networkRejectError;

  /**
   * Default constructor.
   */
  public EquipmentException() {
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
  public EquipmentException(int error, int networkRejectError, String message, Throwable cause) {
    super(message + " " + ModemUtil.getErrorString(error) + " " + ModemUtil.getNetworkErrorString(networkRejectError), cause);
    this.error = error;
    this.networkRejectError = networkRejectError;
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public EquipmentException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param error              is the error.
   * @param networkRejectError is the error.
   * @param message            is the detail message.
   */
  public EquipmentException(int error, int networkRejectError, String message) {
    super(message + " " + ModemUtil.getErrorString(error) + " " + ModemUtil.getNetworkErrorString(networkRejectError));
    this.error = error;
    this.networkRejectError = networkRejectError;
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public EquipmentException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public EquipmentException(Throwable cause) {
    super(cause);
  }

  public int getError() {
    return error;
  }

  public int getNetworkRejectError() {
    return networkRejectError;
  }
}
