package com.github.pmoerenhout.atcommander.module._3gpp.types;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public final class IndexPduMessage extends IndexMessage {

  private String alpha;
  private int length;
  private String pdu;

  public IndexPduMessage(final int index, final MessageStatus status, final int length, final String pdu) {
    super(index, status);
    this.length = length;
    this.pdu = pdu;
  }

  public IndexPduMessage(final int index, final MessageStatus status, final String alpha, final int length, final String pdu) {
    super(index, status);
    this.alpha = alpha;
    this.length = length;
    this.pdu = pdu;
  }

  public String getAlpha() {
    return alpha;
  }

  public int getLength() {
    return length;
  }

  public String getPdu() {
    return pdu;
  }
}
