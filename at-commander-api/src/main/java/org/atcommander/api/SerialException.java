package org.atcommander.api;

public class SerialException extends Exception {


  private static final long serialVersionUID = -1080223550663378266L;

  /**
   * Default constructor.
   */
  public SerialException() {
    super("InitException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public SerialException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public SerialException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public SerialException(Throwable cause) {
    super(cause);
  }

}
