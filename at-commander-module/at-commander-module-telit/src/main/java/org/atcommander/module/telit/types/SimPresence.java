package org.atcommander.module.telit.types;


public final class SimPresence {

  private Integer mode;
  private boolean remote;
  private boolean inserted;

  public SimPresence(final Integer mode, final boolean remote, final boolean inserted) {
    this.mode = mode;
    this.remote = remote;
    this.inserted = inserted;
  }

  public Integer getMode() {
    return mode;
  }

  public boolean isRemote() {
    return remote;
  }

  public boolean isInserted() {
    return inserted;
  }
}
