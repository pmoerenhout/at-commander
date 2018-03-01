package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum WirelessNetwork {
  GSM(12),
  UTRAN(22),
  GERAN_UTRAN(25);

  private final int value;

  WirelessNetwork(final int value) {
    this.value = value;
  }

  public static WirelessNetwork fromInt(final int response) {
    for (WirelessNetwork s : WirelessNetwork.values()) {
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
