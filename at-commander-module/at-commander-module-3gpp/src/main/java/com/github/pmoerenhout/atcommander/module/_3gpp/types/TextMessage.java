package com.github.pmoerenhout.atcommander.module._3gpp.types;

import java.time.ZonedDateTime;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public final class TextMessage extends Message {

  private int index;
  private String oada;
  private ZonedDateTime scts;

  private String text;

  public TextMessage(final MessageStatus status, final String oada, final String alpha, final ZonedDateTime scts, final String text) {
    super(status, alpha);
    this.oada = oada;
    this.scts = scts;
    this.text = text;
  }

  public int getIndex() {
    return index;
  }

  public String getOada() {
    return oada;
  }

  public ZonedDateTime getScts() {
    return scts;
  }

  public String getText() {
    return text;
  }
}
