package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum MessageMode {
  PDU(0),
  TEXT(1);

  private final int value;

  MessageMode(int value) {
    this.value = value;
  }

  public static MessageMode fromInt(final int response) {
    for (final MessageMode s : MessageMode.values()) {
      if (s.value() == response) {
        return s;
      }
    }
    return null;
  }

  public int value() {
    return value;
  }
}
