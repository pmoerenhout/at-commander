package com.github.pmoerenhout.atcommander.module._3gpp.types;

public final class PdpAddress {

  private int cid;
  private String address;

  public PdpAddress(final int cid, final String address) {
    this.cid = cid;
    this.address = address;
  }

  public int getCid() {
    return cid;
  }

  public String getAddress() {
    return address;
  }

}
