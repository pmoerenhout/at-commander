package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum MessageStatus {
  RECEIVED_UNREAD(0, "REC UNREAD"),
  RECEIVED_READ(1, "REC READ"),
  STORED_UNSENT(2, "STO UNSENT"),
  STORED_SENT(3, "STO SENT"),
  ALL(4, "ALL");

  private final int pduValue;
  private final String textValue;

  MessageStatus(final int pduValue, final String textValue) {
    this.pduValue = pduValue;
    this.textValue = textValue;
  }

  public static MessageStatus fromInt(final int response) {
    for (final MessageStatus s : MessageStatus.values()) {
      if (s.pduValue() == response) {
        return s;
      }
    }
    return null;
  }

  public static MessageStatus fromString(final String response) {
    for (final MessageStatus s : MessageStatus.values()) {
      if (s.textValue().equals(response)) {
        System.out.println("OK");
        return s;
      }
    }
    return null;
  }

  public int pduValue() {
    return pduValue;
  }

  public String textValue() {
    return textValue;
  }
}