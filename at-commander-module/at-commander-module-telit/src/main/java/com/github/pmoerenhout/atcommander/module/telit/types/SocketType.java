package com.github.pmoerenhout.atcommander.module.telit.types;


import com.github.pmoerenhout.atcommander.module.v250.enums.Type;
import com.github.pmoerenhout.atcommander.module.telit.enums.Direction;

public class SocketType {

  private int socketId;
  private Type type;
  private Direction direction;

  public SocketType(final int socketId, final Type type, final Direction direction) {
    this.socketId = socketId;
    this.type = type;
    this.direction = direction;
  }

  public int getSid() {
    return socketId;
  }

  public void setSid(final int sid) {
    this.socketId = socketId;
  }

  public Type getType() {
    return type;
  }

  public void setType(final Type type) {
    this.type = type;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(final Direction direction) {
    this.direction = direction;
  }
}
