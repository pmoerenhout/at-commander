package org.atcommander.module.telit.enums;

public enum SringMode {
  NORMAL(0),
  DATA_AMOUNT(1),
  DATA_VIEW(2),
  DATA_VIEW_WITH_UDP(3);

  private final int value;

  SringMode(final int value) {
    this.value = value;
  }

  public static SringMode fromInt(final int response) {
    for (SringMode s : SringMode.values()) {
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
