package com.github.pmoerenhout.atcommander;

public enum DeviceSpeed {
  SPEED_2400(2400),
  SPEED_9600(9600),
  SPEED_38400(38400),
  SPEED_115200(115200);

  private final int value;

  DeviceSpeed(int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
