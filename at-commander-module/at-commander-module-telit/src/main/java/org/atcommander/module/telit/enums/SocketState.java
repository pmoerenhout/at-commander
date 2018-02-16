package org.atcommander.module.telit.enums;

public enum SocketState {
  CLOSED(0),
  OPEN_WITH_ACTIVE_DATA_TRANSFER(1),
  SUSPENDED(2),
  SUSPENDED_WITH_PENDING_DATA(3),
  LISTENING(4),
  INCOMING_WAITING(5),
  RESOLVING_DNS(6),
  CONNECTING(7);

  private final int value;

  SocketState(final int value) {
    this.value = value;
  }

  public static SocketState fromInt(final int response) {
    for (SocketState s : SocketState.values()) {
      if (s.value() == response) {
        return s;
      }
    }
    return null;
  }

  public int value() {
    return value;
  }
}
