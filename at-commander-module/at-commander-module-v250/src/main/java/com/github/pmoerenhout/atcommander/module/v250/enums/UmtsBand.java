package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum UmtsBand {
  FDDI(0),
  FDDII(1),
  FDDV(2),
  FDDI_FDDII_FDDV(3),
  FDDII_FDDV(4),
  FDDVIII(5),
  FDDI_FDDVIII(6),
  FDDIV_AWS(7);

  private final int value;

  UmtsBand(final int value) {
    this.value = value;
  }

  public static UmtsBand fromInt(final int response) {
    for (final UmtsBand s : UmtsBand.values()) {
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
