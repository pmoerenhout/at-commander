package com.github.pmoerenhout.atcommander.module.v250.error;

import com.github.pmoerenhout.atcommander.AbstractFinalResponse;

public class ConnectFinalResponseCode extends AbstractFinalResponse {

  public ConnectFinalResponseCode(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }
}
