package org.atcommander.module.telit.enums;

public enum ClosureType {
  IMMEDIATELY(0),
  SHUTDOWN_ABORTIVE(255);

  private final int value;

  ClosureType(final int value) {
    this.value = value;
  }

  public static ClosureType fromInt(final int response) {
    for (final ClosureType s : ClosureType.values()) {
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
