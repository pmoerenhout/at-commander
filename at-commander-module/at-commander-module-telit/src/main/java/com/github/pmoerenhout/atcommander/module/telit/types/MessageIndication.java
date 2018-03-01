package com.github.pmoerenhout.atcommander.module.telit.types;

import java.time.Instant;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class MessageIndication {

  private Instant timestamp;
  private String store;
  private int index;

  public Instant getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(final Instant timestamp) {
    this.timestamp = timestamp;
  }

  public String getStore() {
    return store;
  }

  public void setStore(final String store) {
    this.store = store;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(final int index) {
    this.index = index;
  }


  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (obj.getClass() != getClass()) {
      return false;
    }
    MessageIndication rhs = (MessageIndication) obj;
    return new EqualsBuilder()
        .append(this.timestamp, rhs.timestamp)
        .append(this.store, rhs.store)
        .append(this.index, rhs.index)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(timestamp)
        .append(store)
        .append(index)
        .toHashCode();
  }
}
