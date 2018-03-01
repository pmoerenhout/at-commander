package com.github.pmoerenhout.atcommander.module.telit.types;


public final class ContextStatus {

  private final int cid;
  private final int status;

  public ContextStatus(final int cid, final int status) {
    this.cid = cid;
    this.status = status;
  }

  public int getCid() {
    return cid;
  }

  public int getStatus() {
    return status;
  }
}
