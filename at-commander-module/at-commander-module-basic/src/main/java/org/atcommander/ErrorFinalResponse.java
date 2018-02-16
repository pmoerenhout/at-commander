package org.atcommander;

import org.atcommander.basic.exceptions.ErrorException;
import org.atcommander.basic.exceptions.ResponseException;

public class ErrorFinalResponse extends FinalResponse2 {

  public ErrorFinalResponse(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() throws ResponseException {
    throw new ErrorException("An error occurred");
  }
}
