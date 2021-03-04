package com.github.pmoerenhout.atcommander;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public final class FinalResponse {

  private FinalResponseCode code;
  private String parameter;

  public FinalResponse(final FinalResponseCode code, final String parameter) {
    this.code = code;
    this.parameter = parameter;
  }

  public FinalResponseCode getCode() {
    return code;
  }

  public String getParameter() {
    return parameter;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    FinalResponse rhs = (FinalResponse) obj;
    return new EqualsBuilder()
        .append(this.code, rhs.code)
        .append(this.parameter, rhs.parameter)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(code)
        .append(parameter)
        .toHashCode();
  }
}
