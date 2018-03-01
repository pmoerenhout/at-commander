package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum ConnectionElement {
  TRANSPARANT(0),
  NON_TRANSPARANT(1);

  private final int value;

  ConnectionElement(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
