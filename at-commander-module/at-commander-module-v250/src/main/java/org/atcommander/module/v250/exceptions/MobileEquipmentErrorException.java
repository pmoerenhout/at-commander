package org.atcommander.module.v250.exceptions;

import org.atcommander.basic.exceptions.ResponseException;

public class MobileEquipmentErrorException extends ResponseException {

  private static final long serialVersionUID = -1984234843472L;

  /**
   * Default constructor.
   */
  public MobileEquipmentErrorException() {
    super("MobileEquipmentErrorException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public MobileEquipmentErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public MobileEquipmentErrorException(final String message, final Integer code) {
    super("CME ERROR" + (code == null ? "" : " " + code) + ": " + message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public MobileEquipmentErrorException(Throwable cause) {
    super(cause);
  }

}
