package com.github.pmoerenhout.atcommander.module._3gpp;

import com.github.pmoerenhout.atcommander.AbstractFinalResponse;

public class MoreDataFinalResponse extends AbstractFinalResponse {

  public MoreDataFinalResponse(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }

}
