package com.github.pmoerenhout.atcommander.module._3gpp.types;


import java.time.ZonedDateTime;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class IndexTextMessage extends IndexMessage {

  private String oada;
  private String alpha;

  private ZonedDateTime scts;

  private Integer toOada;
  private Integer length;

  private String text;

  public IndexTextMessage(final int index, final MessageStatus status, final String oada, final String alpha, final ZonedDateTime scts, final String text) {
    super(index, status);
    this.oada = oada;
    this.alpha = alpha;
    this.scts = scts;
    this.text = text;
  }

  public IndexTextMessage(final int index, final MessageStatus status, final String oada, final String alpha, final ZonedDateTime scts, final int toOada,
                          final int length, final String text) {
    super(index, status);
    this.oada = oada;
    this.alpha = alpha;
    this.scts = scts;
    this.toOada = toOada;
    this.length = length;
    this.text = text;
  }

  public String getOada() {
    return oada;
  }

  public String getAlpha() {
    return alpha;
  }

  public ZonedDateTime getScts() {
    return scts;
  }

  public Integer getToOada() {
    return toOada;
  }

  public Integer getLength() {
    return length;
  }

  public String getText() {
    return text;
  }
}
