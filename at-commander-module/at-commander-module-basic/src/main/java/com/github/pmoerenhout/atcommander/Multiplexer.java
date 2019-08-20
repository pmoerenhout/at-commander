//package com.github.pmoerenhout.atcommander;
//
//import java.io.FilterInputStream;
//import java.io.FilterOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import com.github.pmoerenhout.atcommander.api.Mode;
//
//public class Multiplexer extends OutputStream {
//
//  private OutputStream outputStream;
//
//
//  private OutputStream commandOutputStream;
//  private OutputStream dataOutputStream;
//
//  private Mode mode = Mode.COMMAND;
//
//  public Multiplexer(final OutputStream commandOutputStream, final OutputStream outputStream) {
//    this.commandOutputStream = commandOutputStream;
//    this.outputStream = outputStream;
//  }
//
//  public OutputStream getCommandOutputStream() {
//    return commandOutputStream;
//  }
//
//  public OutputStream getDataOutputStream() {
//    return dataOutputStream;
//  }
//
//  public void setMode(final Mode mode) {
//    this.mode = mode;
//  }
//
//
//
//}
