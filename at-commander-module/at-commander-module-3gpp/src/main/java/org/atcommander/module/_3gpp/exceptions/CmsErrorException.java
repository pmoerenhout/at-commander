package org.atcommander.module._3gpp.exceptions;

import org.atcommander.basic.exceptions.ResponseException;

public class CmsErrorException extends ResponseException {

  private static final long serialVersionUID = -3865341039286154694L;

  public CmsErrorException() {
  }

  public CmsErrorException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public CmsErrorException(final String message) {
    super(message);
  }

  public CmsErrorException(final Throwable cause) {
    super(cause);
  }
}
