package com.github.pmoerenhout.atcommander.module.telit.enums;

public enum GsmBand {
  GSM900_DCS1800(0),
  GSM900_PCS1900(1),
  GSM850_DCS1800(2),
  GSM850_PCS1900(3);

  private final int value;

  GsmBand(final int value) {
    this.value = value;
  }

  public static GsmBand fromInt(final int response) {
    for (final GsmBand s : GsmBand.values()) {
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
