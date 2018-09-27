package com.github.pmoerenhout.atcommander.module._3gpp.types;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public final class PduMessage extends Message {

  private int length;
  private String pdu;

  public PduMessage(final MessageStatus status, final String alpha, final int length, final String pdu) {
    super(status, alpha);
    this.length = length;
    this.pdu = pdu;
  }

  public int getLength() {
    return length;
  }

  public String getPdu() {
    return pdu;
  }
}
