package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum Type {
  NO(0),
  TCP_SOCKET(1),
  UDP_SOCKET(2);

  private final int value;

  Type(final int value) {
    this.value = value;
  }

  public static Type fromInt(final int response) {
    for (final Type s : Type.values()) {
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