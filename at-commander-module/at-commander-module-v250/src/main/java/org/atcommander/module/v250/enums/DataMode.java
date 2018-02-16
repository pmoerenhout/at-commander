package org.atcommander.module.v250.enums;

public enum DataMode {
  TEXT(0),
  HEXADECIMAL(1);

  private final int value;

  DataMode(final int value) {
    this.value = value;
  }

  public static DataMode fromInt(final int response) {
    for (DataMode s : DataMode.values()) {
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
