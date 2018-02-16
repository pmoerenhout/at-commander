package org.atcommander.module.v250.enums;

public enum PacketServiceNetworkType {
  GPRS(0),
  EDGE(1),
  WCDMA(2),
  HSDPA(3),
  UNKNOWN(4);

  private final int value;

  PacketServiceNetworkType(final int value) {
    this.value = value;
  }

  public static PacketServiceNetworkType fromInt(final int response) {
    for (PacketServiceNetworkType s : PacketServiceNetworkType.values()) {
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
