package com.github.pmoerenhout.atcommander.module._3gpp.types;


import java.time.ZonedDateTime;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class IndexTextMessage extends IndexMessage {

  private String alpha;

  private String oada;
  private ZonedDateTime scts;

  private String text;


  public IndexTextMessage(final int index, final MessageStatus status, final String oada, final String alpha, final ZonedDateTime scts, final String text) {
    super(index, status);
    this.oada = oada;
    this.alpha = alpha;
    this.scts = scts;
    this.text = text;
  }

  public String getAlpha() {
    return alpha;
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
