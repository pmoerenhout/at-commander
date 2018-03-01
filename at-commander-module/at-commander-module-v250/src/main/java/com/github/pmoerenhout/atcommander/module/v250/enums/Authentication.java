package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum Authentication {
  NONE(0),
  PAP(1),
  CHAP(2),
  AUTOMATIC(3);

  private final int value;

  Authentication(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
