package com.github.pmoerenhout.atcommander.module.telit.types;


public class  GprsDataVolume {

  private int cid;
  private int total;
  private int sent;
  private int received;

  public GprsDataVolume() {
  }

  public GprsDataVolume(final int cid, final int total, final int sent, final int received) {
    this.cid = cid;
    this.total = total;
    this.sent = sent;
    this.received = received;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(final int cid) {
    this.cid = cid;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(final int total) {
    this.total = total;
  }

  public int getSent() {
    return sent;
  }

  public void setSent(final int sent) {
    this.sent = sent;
  }

  public int getReceived() {
    return received;
  }

  public void setReceived(final int received) {
    this.received = received;
  }
}
