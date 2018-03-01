package com.github.pmoerenhout.atcommander.module.telit.types;

public class SocketInformation {

  private int socketId;
  private int sent;
  private int received;
  private int bufferedInput;
  private int acknowledgeWaiting;

  public SocketInformation(final int socketId, final int sent, final int received, final int bufferedInput,
                           final int acknowledgeWaiting) {
    this.socketId = socketId;
    this.sent = sent;
    this.received = received;
    this.bufferedInput = bufferedInput;
    this.acknowledgeWaiting = acknowledgeWaiting;
  }

  public int getSocketId() {
    return socketId;
  }

  public int getSent() {
    return sent;
  }

  public int getReceived() {
    return received;
  }

  public int getBufferedInput() {
    return bufferedInput;
  }

  public int getAcknowledgeWaiting() {
    return acknowledgeWaiting;
  }
}
