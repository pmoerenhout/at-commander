package org.atcommander.module.telit.enums;

public enum Direction {
  NO(0),
  DIALER(1),
  LISTENER(2);

  private final int value;

  Direction(final int value) {
    this.value = value;
  }

  public static Direction fromInt(final int response) {
    for (final Direction s : Direction.values()) {
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