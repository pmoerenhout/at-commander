package org.atcommander.module.telit.types;


public class Ping {

  private int replyNumber;
  private String remoteIpAddress;
  private int replyTime;
  private int timeToLive;

  public Ping(final int replyNumber, final String remoteIpAddress, final int replyTime, final int timeToLive) {
    this.replyNumber = replyNumber;
    this.remoteIpAddress = remoteIpAddress;
    this.replyTime = replyTime;
    this.timeToLive = timeToLive;
  }

  public int getReplyNumber() {
    return replyNumber;
  }

  public void setReplyNumber(final int replyNumber) {
    this.replyNumber = replyNumber;
  }

  public String getRemoteIpAddress() {
    return remoteIpAddress;
  }

  public void setRemoteIpAddress(final String remoteIpAddress) {
    this.remoteIpAddress = remoteIpAddress;
  }

  public int getReplyTime() {
    return replyTime;
  }

  public void setReplyTime(final int replyTime) {
    this.replyTime = replyTime;
  }

  public int getTimeToLive() {
    return timeToLive;
  }

  public void setTimeToLive(final int timeToLive) {
    this.timeToLive = timeToLive;
  }
}
