package com.github.pmoerenhout.atcommander.module.telit;

import com.github.pmoerenhout.atcommander.FinalResponse2;

public class MoreDataFinalResponse extends FinalResponse2 {

  public MoreDataFinalResponse(final String line) {
    super(line);
  }

  @Override
  public void throwIfNeccessary() {
    return;
  }

}
