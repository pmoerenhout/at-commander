package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum Status {

  UNKNOWN(0),
  AVAILABLE(1),
  CURRENT(2),
  FORBIDDEN(3);

  private final int value;

  Status(final int value) {
    this.value = value;
  }

  public static Status fromInt(final int response) {
    for (Status s : Status.values()) {
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
