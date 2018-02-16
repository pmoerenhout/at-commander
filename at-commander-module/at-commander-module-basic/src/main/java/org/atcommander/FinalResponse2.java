package org.atcommander;

import org.atcommander.basic.exceptions.ResponseException;

public abstract class FinalResponse2 {

  protected String line;

  public FinalResponse2(final String line) {
    this.line = line;
  }

  public String getLine() {
    return line;
  }

  public void throwIfNeccessary() throws ResponseException {
    throw new IllegalStateException("To be implemented by subclass");
  }

}
