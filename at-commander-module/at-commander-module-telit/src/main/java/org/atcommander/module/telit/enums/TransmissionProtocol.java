package org.atcommander.module.telit.enums;

public enum TransmissionProtocol {
  TCP(0),
  UDP(1);

  private final int value;

  TransmissionProtocol(final int value) {
    this.value = value;
  }

  public int value() {
    return value;
  }
}
