package com.github.pmoerenhout.atcommander.module._3gpp.types;

import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;
import com.github.pmoerenhout.atcommander.module.v250.enums.Status;

public final class Operator {
  // 0=unknown, 1=available, 2=current, 3=forbidden
  private final Status status;
  private final String operatorLong;
  private final String operatorShort;
  private final String operatorNumeric;
  private final AccessTechnology accessTechnology;

  public Operator(final Status status, final String operatorLong, final String operatorShort, final String operatorNumeric) {
    this.status = status;
    this.operatorLong = operatorLong;
    this.operatorShort = operatorShort;
    this.operatorNumeric = operatorNumeric;
    this.accessTechnology = null;
  }

  public Operator(final Status status, final String operatorLong, final String operatorShort, final String operatorNumeric,
                  final AccessTechnology accessTechnology) {
    this.status = status;
    this.operatorLong = operatorLong;
    this.operatorShort = operatorShort;
    this.operatorNumeric = operatorNumeric;
    this.accessTechnology = accessTechnology;
  }

  public Status getStatus() {
    return status;
  }

  public String getOperatorLong() {
    return operatorLong;
  }

  public String getOperatorShort() {
    return operatorShort;
  }

  public String getOperatorNumeric() {
    return operatorNumeric;
  }

  public AccessTechnology getAccessTechnology() {
    return accessTechnology;
  }
}
