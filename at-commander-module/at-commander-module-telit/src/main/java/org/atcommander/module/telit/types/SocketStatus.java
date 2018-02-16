package org.atcommander.module.telit.types;


import org.atcommander.module.telit.enums.SocketState;

public class SocketStatus {

  private int socketId;
  private SocketState state;
  private String localIpAddress;
  private Integer localPort;
  private String remoteIpAddress;
  private Integer remotePort;

  public SocketStatus() {
  }

  public SocketStatus(final int cid, final SocketState state) {
    this.socketId = cid;
    this.state = state;
  }

  public SocketStatus(final int cid, final SocketState state, final String localIpAddress, final int localPort) {
    this.socketId = cid;
    this.state = state;
    this.localIpAddress = localIpAddress;
    this.localPort = localPort;
  }

  public SocketStatus(final int cid, final SocketState state, final String localIpAddress, final int localPort,
                      final String remoteIpAddress, final int remotePort) {
    this.socketId = cid;
    this.state = state;
    this.localIpAddress = localIpAddress;
    this.localPort = localPort;
    this.remoteIpAddress = remoteIpAddress;
    this.remotePort = remotePort;
  }

  public int getSocketId() {
    return socketId;
  }

  public void setSocketId(final int socketId) {
    this.socketId = socketId;
  }

  public SocketState getState() {
    return state;
  }

  public void setState(final SocketState state) {
    this.state = state;
  }

  public String getLocalIpAddress() {
    return localIpAddress;
  }

  public void setLocalIpAddress(final String localIpAddress) {
    this.localIpAddress = localIpAddress;
  }

  public Integer getLocalPort() {
    return localPort;
  }

  public void setLocalPort(final Integer localPort) {
    this.localPort = localPort;
  }

  public String getRemoteIpAddress() {
    return remoteIpAddress;
  }

  public void setRemoteIpAddress(final String remoteIpAddress) {
    this.remoteIpAddress = remoteIpAddress;
  }

  public Integer getRemotePort() {
    return remotePort;
  }

  public void setRemotePort(final Integer remotePort) {
    this.remotePort = remotePort;
  }
}
