package com.github.pmoerenhout.atcommander.module._3gpp.types;

import java.time.ZonedDateTime;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public final class TextMessage extends Message {

  private String oada;
  private ZonedDateTime scts;
  private Integer toOa;
  private Integer firstOctet;
  private Integer pid;
  private Integer dcs;
  private String sca;
  private Integer toSca;
  private Integer length;

  private String text;

  public TextMessage(final MessageStatus status, final String oada, final String alpha, final ZonedDateTime scts, final String text) {
    super(status, alpha);
    this.oada = oada;
    this.scts = scts;
    this.text = text;
  }

  public TextMessage(final MessageStatus status, final String oada, final String alpha, final ZonedDateTime scts,
                     final int toOa, final int firstOctet, final int pid, final int dcs,
                     final String sca, final int toSca, final int length,
                     final String text) {
    super(status, alpha);
    this.oada = oada;
    this.scts = scts;
    this.toOa = toOa;
    this.firstOctet = firstOctet;
    this.pid = pid;
    this.dcs = dcs;
    this.sca = sca;
    this.toSca = toSca;
    this.length = length;
    this.text = text;
  }

  public String getOada() {
    return oada;
  }

  public ZonedDateTime getScts() {
    return scts;
  }

  public Integer getToOa() {
    return toOa;
  }

  public Integer getFirstOctet() {
    return firstOctet;
  }

  public Integer getPid() {
    return pid;
  }

  public Integer getDcs() {
    return dcs;
  }

  public String getSca() {
    return sca;
  }

  public Integer getToSca() {
    return toSca;
  }

  public Integer getLength() {
    return length;
  }

  public String getText() {
    return text;
  }
}
