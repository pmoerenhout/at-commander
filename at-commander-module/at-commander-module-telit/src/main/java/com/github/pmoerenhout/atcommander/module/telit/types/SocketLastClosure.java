package com.github.pmoerenhout.atcommander.module.telit.types;


public class SocketLastClosure {

  private int socketId;
  private int cause;

  public SocketLastClosure(final int socketId, final int cause) {
    this.socketId = socketId;
    this.cause = cause;
  }

  public int getSid() {
    return socketId;
  }

  public void setSid(final int socketId) {
    this.socketId = socketId;
  }

  public int getCause() {
    return cause;
  }

  public void setCause(final int cause) {
    this.cause = cause;
  }
}