package com.github.pmoerenhout.atcommander.module.telit.enums;

public enum GpsResetType {
  FACTORY_RESET(0),
  COLD_START(1),
  WARM_START(2),
  HOT_START(3);

  private final int value;

  GpsResetType(final int value) {
    this.value = value;
  }

  public static GpsResetType fromInt(final int response) {
    for (final GpsResetType s : GpsResetType.values()) {
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
