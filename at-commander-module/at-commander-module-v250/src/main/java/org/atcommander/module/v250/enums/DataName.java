package org.atcommander.module.v250.enums;

public enum DataName {
  ASYNCHRONOUS(0);

  private final int value;

  DataName(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
