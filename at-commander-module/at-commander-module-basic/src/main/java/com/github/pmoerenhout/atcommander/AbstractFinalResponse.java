package com.github.pmoerenhout.atcommander;

import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;

public abstract class AbstractFinalResponse {

  protected String line;

  public AbstractFinalResponse(final String line) {
    this.line = line;
  }

  public String getLine() {
    return line;
  }

  public void throwIfNeccessary() throws ResponseException {
    throw new IllegalStateException("To be implemented by subclass");
  }

}
