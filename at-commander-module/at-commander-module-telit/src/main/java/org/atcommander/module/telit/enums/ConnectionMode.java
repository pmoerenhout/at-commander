package org.atcommander.module.telit.enums;

public enum ConnectionMode {
  ONLINE(0),
  COMMAND(1);

  private final int value;

  ConnectionMode(final int value) {
    this.value = value;
  }

  public static ConnectionMode fromInt(final int response) {
    for (ConnectionMode s : ConnectionMode.values()) {
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
