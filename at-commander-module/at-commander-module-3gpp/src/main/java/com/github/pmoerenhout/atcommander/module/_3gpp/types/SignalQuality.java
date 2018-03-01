package com.github.pmoerenhout.atcommander.module._3gpp.types;

public class SignalQuality {

  private int rssi;
  private int ber;

  public SignalQuality(final int rssi, final int ber) {
    this.rssi = rssi;
    this.ber = ber;
  }

  public int getRssi() {
    return rssi;
  }

  public int getBer() {
    return ber;
  }
}
