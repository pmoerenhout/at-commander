package org.atcommander.module._3gpp.exceptions;

import org.atcommander.basic.exceptions.ResponseException;

public class CmeErrorException extends ResponseException {


  private static final long serialVersionUID = 5637116006883330319L;

  public CmeErrorException() {
  }

  public CmeErrorException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public CmeErrorException(final String message) {
    super(message);
  }

  public CmeErrorException(final Throwable cause) {
    super(cause);
  }
}
