package org.atcommander.module._3gpp.types;

public class GprsAct {

  private int cid;
  private boolean active;

  public GprsAct() {
  }

  public GprsAct(final int cid, final boolean active) {
    this.cid = cid;
    this.active = active;
  }

  public int getCid() {
    return cid;
  }

  public void setCid(final int cid) {
    this.cid = cid;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(final boolean active) {
    this.active = active;
  }
}
