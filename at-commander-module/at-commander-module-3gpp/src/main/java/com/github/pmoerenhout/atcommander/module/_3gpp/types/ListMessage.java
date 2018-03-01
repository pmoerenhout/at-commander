package com.github.pmoerenhout.atcommander.module._3gpp.types;


import java.time.ZonedDateTime;

import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class ListMessage {

  private int index;
  private MessageStatus status;
  private String alpha;
  private int length;

  private String oada;
  private ZonedDateTime scts;

  private String pdu;
  private String text;

  public ListMessage() {
  }

  public ListMessage(final int index, final MessageStatus status, final String alpha, final int length, final String pdu) {
    this.index = index;
    this.status = status;
    this.alpha = alpha;
    this.length = length;
    this.pdu = pdu;
  }

  public ListMessage(final int index, final MessageStatus status, final String oada, final String alpha, final ZonedDateTime scts, final String text) {
    this.index = index;
    this.status = status;
    this.oada = oada;
    this.alpha = alpha;
    this.scts = scts;
    this.text = text;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(final int index) {
    this.index = index;
  }

  public MessageStatus getStatus() {
    return status;
  }

  public void setStatus(final MessageStatus status) {
    this.status = status;
  }

  public String getAlpha() {
    return alpha;
  }

  public void setAlpha(final String alpha) {
    this.alpha = alpha;
  }

  public int getLength() {
    return length;
  }

  public void setLength(final int length) {
    this.length = length;
  }

  public String getOada() {
    return oada;
  }

  public void setOada(final String oada) {
    this.oada = oada;
  }

  public ZonedDateTime getScts() {
    return scts;
  }

  public void setScts(final ZonedDateTime scts) {
    this.scts = scts;
  }

  public String getPdu() {
    return pdu;
  }

  public void setPdu(final String pdu) {
    this.pdu = pdu;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }
}
