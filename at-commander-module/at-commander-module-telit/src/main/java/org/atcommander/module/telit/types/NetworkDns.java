package org.atcommander.module.telit.types;


public class NetworkDns {

  private int cid;
  private String primaryDnsAddress;
  private String secondaryDnsAddress;

  public NetworkDns(final int cid, final String primaryDnsAddress, final String secondaryDnsAddress) {
    this.cid = cid;
    this.primaryDnsAddress = primaryDnsAddress;
    this.secondaryDnsAddress = secondaryDnsAddress;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(final int cid) {
    this.cid = cid;
  }

  public String getPrimaryDnsAddress() {
    return primaryDnsAddress;
  }

  public void setPrimaryDnsAddress(final String primaryDnsAddress) {
    this.primaryDnsAddress = primaryDnsAddress;
  }

  public String getSecondaryDnsAddress() {
    return secondaryDnsAddress;
  }

  public void setSecondaryDnsAddress(final String secondaryDnsAddress) {
    this.secondaryDnsAddress = secondaryDnsAddress;
  }
}
