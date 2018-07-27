package com.github.pmoerenhout.atcommander;

import java.util.List;

public class AtUnsolicitedResponse {

  private List<String> informationalText;

  public AtUnsolicitedResponse(final List<String> lines) {
    this.informationalText = lines;
  }

  public List<String> getInformationalText() {
    return informationalText;
  }

}
