package com.github.pmoerenhout.atcommander;

public class OkFinalResponse extends AbstractFinalResponse {

  public OkFinalResponse(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }
}
