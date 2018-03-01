package com.github.pmoerenhout.atcommander.module.telit.enums;

public enum GprsDataVolumeMode {
  RESET_ALL(0),
  SHOW_ACTIVE(1),
  SHOW_ACTIVE_SINCE_RESET(2);

  private final int value;

  GprsDataVolumeMode(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
