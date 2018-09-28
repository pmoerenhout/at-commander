package com.github.pmoerenhout.atcommander.module._3gpp.types;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class IndexMessage {

  private int index;
  private MessageStatus status;

  public IndexMessage(final int index, final MessageStatus status) {
    this.index = index;
    this.status = status;
  }

  public int getIndex() {
    return index;
  }

  public MessageStatus getStatus() {
    return status;
  }
}
