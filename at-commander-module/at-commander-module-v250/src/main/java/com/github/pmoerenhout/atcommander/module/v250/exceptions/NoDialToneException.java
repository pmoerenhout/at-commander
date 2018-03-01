package com.github.pmoerenhout.atcommander.module.v250.exceptions;

public class NoDialToneException extends DialException {

  private static final long serialVersionUID = -19842598865483212L;

  /**
   * Default constructor.
   */
  public NoDialToneException() {
    super("NoDialToneException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public NoDialToneException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public NoDialToneException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public NoDialToneException(Throwable cause) {
    super(cause);
  }

}
