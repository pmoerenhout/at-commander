package com.github.pmoerenhout.atcommander.module.telit.enums;

public enum JammedDetectReportMode {
  DISABLED(0),
  ENABLED_GPIO(1),
  ENABLED_UNSOLICITED(2),
  ENABLED_GPIO_UNSOLICITED(3),
  ENABLED_UNSOLICITED_3_SECONDS(4),
  ENABLED_GPIO_UNSOLICITED_3_SECONDS(5),
  ENABLED_FORMAT(6);

  private final int value;

  JammedDetectReportMode(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
