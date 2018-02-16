package org.atcommander.module._3gpp;

public enum RegistrationState {

  NOT_REGISTERED(0),
  REGISTERED_HOME_NETWORK(1),
  SEARCHING(2),
  DENIED(3),
  UNKNOWN(4),
  REGISTERED_ROAMING(5);

  private int state;

  RegistrationState(final int state) {
    this.state = state;
  }

  public static RegistrationState fromString(final String response) {
    if (response != null) {
      for (RegistrationState s : RegistrationState.values()) {
        if (s.getState() == Integer.parseInt(response)) {
          return s;
        }

      }
    }
    return null;
  }

  public int getState() {
    return state;
  }

  public boolean isRegistered() {
    return (state == 1 || state == 5);
  }

}
