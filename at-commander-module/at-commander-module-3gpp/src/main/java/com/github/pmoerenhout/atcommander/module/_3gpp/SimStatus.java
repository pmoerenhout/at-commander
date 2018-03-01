package com.github.pmoerenhout.atcommander.module._3gpp;

public enum SimStatus {

  NOT_INSERTED(0),
  INSERTED(1),
  INSERTED_PIN_UNLOCKED(2),
  INSERTED_READY(3),;

  int status;

  SimStatus(final int status) {
    this.status = status;
  }

  public static SimStatus fromInt(final int status) {
      for (final SimStatus s : SimStatus.values()) {
        if (s.getStatus() == status) {
          return s;
        }
    }
    return null;
  }

  public int getStatus() {
    return status;
  }

}
