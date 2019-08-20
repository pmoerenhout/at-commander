package com.github.pmoerenhout.atcommander.api;

import java.io.InputStream;
import java.io.OutputStream;

public interface SerialInterface {

  String getId();

  void init() throws SerialException;

  // void write(byte[] data) throws SerialException;

  OutputStream getOutputStream();

  InputStream getInputStream();

  byte[] read();

  void close();

  Mode getMode();

  void setMode(Mode mode);

  boolean isDsr();

  boolean isCts();

  boolean isCd();

  void setDtr(boolean state);

  void sendBreak(int duration);

  void panic();

  void setSolicitedResponseCallback(SolicitedResponseCallback solicitedResponseCallback);

  void addUnsolicited(UnsolicitedPatternClass unsolicitedPatternClass);

}
