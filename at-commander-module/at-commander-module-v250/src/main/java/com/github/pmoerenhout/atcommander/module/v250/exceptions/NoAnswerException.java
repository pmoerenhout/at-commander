package com.github.pmoerenhout.atcommander.module.v250.exceptions;

public class NoAnswerException extends DialException {

  private static final long serialVersionUID = -19842598396110598L;

  /**
   * Default constructor.
   */
  public NoAnswerException() {
    super();
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public NoAnswerException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public NoAnswerException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public NoAnswerException(Throwable cause) {
    super(cause);
  }

}
