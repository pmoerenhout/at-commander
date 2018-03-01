package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum OperatorSelectionMode {

  AUTOMATIC(0),
  MANUAL_UNLOCKED(1),
  DEREGISTER(2),
  SET_ONLY(3),
  MANUAL_AUTOMATIC(4);
  //MANUAL_LOCKED(5);

  private final int value;

  OperatorSelectionMode(int value) {
    this.value = value;
  }

  public static OperatorSelectionMode fromInt(final int response) {
    for (OperatorSelectionMode o : OperatorSelectionMode.values()) {
      if (o.value() == response) {
        return o;
      }
    }
    return null;
  }

  public int value() {
    return value;
  }
}
