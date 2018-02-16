package org.atcommander.module._3gpp.types;

public class FacilityStatus {

  private int status;
  private Integer clazz;

  public FacilityStatus(final int status) {
    this.status = status;
  }

  public FacilityStatus(final int status, final Integer clazz) {
    this.status = status;
    this.clazz = clazz;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(final int status) {
    this.status = status;
  }

  public Integer getClazz() {
    return clazz;
  }

  public void setClazz(final Integer clazz) {
    this.clazz = clazz;
  }
}
