package com.github.pmoerenhout.atcommander.module.v250.enums;

public enum PinStatus {
  READY("READY"),
  SIM_PIN("SIM PIN"),
  SIM_PUK("SIM PUK"),
  PH_SIM_PIN("PH-SIM PIN"),
  PH_FSIM_PIN("PH-FSIM PIN"),
  PH_FSIM_PUK("PH-FSIM PUK"),
  SIM_PIN2("SIM PIN2"),
  SIM_PUK2("SIM PUK2"),
  PH_NET_PIN("PH-NET PIN"),
  PH_NET_PUK("PH-NET PUK"),
  PH_SUBNET_PIN("PH-SUBNET PIN"),
  PH_SUBNET_PUK("PH-SUBNET PUK"),
  PH_SP_PIN("PH-SP PIN"),
  PH_SP_PUK("PH-SP PUK"),
  PH_CORP_PIN("PH-CORP PIN"),
  PH_CORP_PUK("PH-CORP PUK"),
  PH_MCL_PIN("PH-MCL PIN");

  private final String value;

  PinStatus(final String value) {
    this.value = value;
  }

  public static PinStatus fromString(final String response) {
    if (response != null) {
      for (PinStatus s : PinStatus.values()) {
        if (s.getValue().equals(response)) {
          return s;
        }
      }
    }
    return null;
  }

  public String value() {
    return value;
  }

  public String getValue() {
    return value;
  }
}
