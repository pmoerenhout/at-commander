package com.github.pmoerenhout.atcommander.module.telit;

import com.github.pmoerenhout.atcommander.AbstractFinalResponse;

public class ReleasedFinalResponse extends AbstractFinalResponse {

  public ReleasedFinalResponse(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }

}
