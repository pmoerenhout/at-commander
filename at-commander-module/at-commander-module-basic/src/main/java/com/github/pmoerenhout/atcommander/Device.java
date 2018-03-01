package com.github.pmoerenhout.atcommander;

import java.util.UUID;

public class Device {

  private String id;
  private String port;
  private int speed;
  private String productName;
  private String productSerialNumber;

  public Device() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public String getPort() {
    return port;
  }

  public void setPort(final String port) {
    this.port = port;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(final int speed) {
    this.speed = speed;
  }

  public String getProductName() {
    return productName;
  }

  public void setProductName(final String productName) {
    this.productName = productName;
  }

  public String getProductSerialNumber() {
    return productSerialNumber;
  }

  public void setProductSerialNumber(final String productSerialNumber) {
    this.productSerialNumber = productSerialNumber;
  }
}
