package com.github.pmoerenhout.atcommander.module.telit;

import com.github.pmoerenhout.atcommander.FinalResponse2;

public class ReleasedFinalResponse extends FinalResponse2 {

  public ReleasedFinalResponse(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }

}
