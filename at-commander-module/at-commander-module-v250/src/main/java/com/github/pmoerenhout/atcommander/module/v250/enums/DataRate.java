package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum DataRate {
  AUTO_BAUD(0),
  V21_300_BPS(1),
  V22_1200_BPS(2),
  V23_1200_75_BPS(3),
  V22BIS_2400_BPS(4),
  V32_4800_BPS(6),
  V32_9600_BPS(7),
  V34_14000_BPS(14),
  V110_300_BPS(65),
  V110_1200_BPS(66),
  V110_2400_BPS(68),
  V110_4800_BPS(70),
  V110_9600_BPS(71),
  V110_14400_BPS(75);

  private final int value;

  DataRate(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
