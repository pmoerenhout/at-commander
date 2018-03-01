package com.github.pmoerenhout.atcommander.module.v250.error;

import com.github.pmoerenhout.atcommander.FinalResponse2;

public class ConnectFinalResponseCode extends FinalResponse2 {

  public ConnectFinalResponseCode(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }
}
