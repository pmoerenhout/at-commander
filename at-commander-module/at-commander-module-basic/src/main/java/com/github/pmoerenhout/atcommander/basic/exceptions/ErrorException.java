package com.github.pmoerenhout.atcommander.basic.exceptions;

public class ErrorException extends ResponseException {

  private static final long serialVersionUID = -6787573899948158417L;

  /**
   * Default constructor.
   */
  public ErrorException() {
    super("ErrorException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public ErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public ErrorException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public ErrorException(Throwable cause) {
    super(cause);
  }

}
