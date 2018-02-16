package org.atcommander.module.v250.error;

import org.atcommander.FinalResponse2;

public class ConnectFinalResponseCode extends FinalResponse2 {

  public ConnectFinalResponseCode(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }
}
