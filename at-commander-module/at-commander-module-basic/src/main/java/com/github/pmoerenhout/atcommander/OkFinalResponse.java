package com.github.pmoerenhout.atcommander;

public class OkFinalResponse extends FinalResponse2 {

  public OkFinalResponse(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }
}
