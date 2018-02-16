package org.atcommander.module.v250.types;

public enum DialStatus {
  OK(0),
  CONNECT(1),
  NO_CARRIER(3),
  ERROR(4),
  NO_DIALTONE(6),
  BUSY(7),
  NO_ANSWER(8);

  private final int value;

  DialStatus(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
