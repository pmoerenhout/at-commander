package org.atcommander.api;

public interface SerialInterface {

  String getId();

  void init() throws SerialException;

  void write(byte[] data) throws SerialException;

  byte[] read();

  void close();

  State getState();

  boolean isDsr();

  boolean isCts();

  boolean isCd();

  void setDtr(boolean state);

  void sendBreak(int duration);

  void panic();

  void setSolicitedResponseCallback(SolicitedResponseCallback solicitedResponseCallback);

  void addUnsolicited(UnsolicitedPatternClass unsolicitedPatternClass);

}
