package org.atcommander.module.v250.enums;

public enum Functionality {

  MINIMUM(0),
  FULL(1),
  DISABLE_TX_RX(4),
  FULL_POWER_SAVING(5),
  CYCLIC_SLEEP(7),
  MINIMUM_2(9),
  FAST_DETACH(12);

  private final int value;

  Functionality(final int value) {
    this.value = value;
  }

  public static Functionality fromInt(final int response) {
    for (Functionality s : Functionality.values()) {
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
