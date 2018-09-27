package com.github.pmoerenhout.atcommander.module._3gpp.types;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class Message {

  private MessageStatus status;
  private String alpha;

  public Message(final MessageStatus status, final String alpha) {
    this.status = status;
    this.alpha = alpha;
  }

  public MessageStatus getStatus() {
    return status;
  }

  public String getAlpha() {
    return alpha;
  }
}
