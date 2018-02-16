package org.atcommander.basic.exceptions;

public abstract class ResponseException extends Exception {

  private static final long serialVersionUID = -2168239768788392364L;

  /**
   * Default constructor.
   */
  public ResponseException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public ResponseException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public ResponseException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public ResponseException(Throwable cause) {
    super(cause);
  }

}
