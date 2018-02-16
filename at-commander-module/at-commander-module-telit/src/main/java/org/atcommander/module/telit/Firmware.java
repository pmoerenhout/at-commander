package org.atcommander.module.telit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Firmware implements Comparable<Firmware> {
  private int major;
  private int minor;
  private int feature;

  public Firmware(final int major, final int minor, final int feature) {
    this.major = major;
    this.minor = minor;
    this.feature = feature;
  }

  public int getMajor() {
    return major;
  }

  public int getMinor() {
    return minor;
  }

  public int getFeature() {
    return feature;
  }

  @Override
  public int compareTo(final Firmware o) {
    if (major < o.major) {
      return -1;
    } else if (major > o.major) {
      return 1;
    } else {
      if (minor < o.minor) {
        return -1;
      } else if (minor > o.minor) {
        return 1;
      } else {
        if (feature < o.feature) {
          return -1;
        } else if (feature > o.feature) {
          return 1;
        } else {
          return 0;
        }
      }
    }

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
    Firmware rhs = (Firmware) obj;
    return new EqualsBuilder()
        .append(this.major, rhs.major)
        .append(this.minor, rhs.minor)
        .append(this.feature, rhs.feature)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder()
        .append(major)
        .append(minor)
        .append(feature)
        .toHashCode();
  }


  @Override
  public String toString() {
    return new ToStringBuilder(this)
        .append("major", major)
        .append("minor", minor)
        .append("feature", feature)
        .toString();
  }
}
