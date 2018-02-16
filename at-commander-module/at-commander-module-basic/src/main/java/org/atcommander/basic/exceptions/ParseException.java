package org.atcommander.basic.exceptions;

public class ParseException extends RuntimeException {

  private static final long serialVersionUID = 4144157288578354938L;

  /**
   * Default constructor.
   */
  public ParseException() {
    super("ParseException");
  }

  /**
   * Construct with specified message and cause.
   *
   * @param message is the detail message.
   * @param cause   is the parent cause.
   */
  public ParseException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Construct with specified message.
   *
   * @param message is the detail message.
   */
  public ParseException(String message) {
    super(message);
  }

  /**
   * Construct with specified cause.
   *
   * @param cause is the parent cause.
   */
  public ParseException(Throwable cause) {
    super(cause);
  }

}
