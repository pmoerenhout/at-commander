package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.module.telit.enums.SringMode;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.DataMode;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SocketConfigurationExtendedCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SCFGEXT = "#SCFGEXT";

  private int socketId;
  private SringMode sringMode;
  private DataMode receivedDataMode;
  private int keepalive;
  private Boolean listenAutoResponse;
  private DataMode sendDataMode;

  public SocketConfigurationExtendedCommand(final AtCommander atCommander, final int socketId,
                                            final SringMode sringMode,
                                            final DataMode receivedDataMode,
                                            final int keepalive) {
    super(COMMAND_SCFGEXT, atCommander);
    this.socketId = socketId;
    this.sringMode = sringMode;
    this.receivedDataMode = receivedDataMode;
    this.keepalive = keepalive;
  }

  public SocketConfigurationExtendedCommand(final AtCommander atCommander, final int socketId,
                                            final SringMode sringMode,
                                            final DataMode receivedDataMode,
                                            final int keepalive, final boolean listenAutoResponse,
                                            final DataMode sendDataMode) {
    super(COMMAND_SCFGEXT, atCommander);
    this.socketId = socketId;
    this.sringMode = sringMode;
    this.receivedDataMode = receivedDataMode;
    this.keepalive = keepalive;
    this.listenAutoResponse = listenAutoResponse;
    this.sendDataMode = sendDataMode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SCFGEXT);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(String.valueOf(sringMode.value()));
      sb.append(COMMA);
      sb.append(String.valueOf(receivedDataMode.value()));
      sb.append(COMMA);
      sb.append(String.valueOf(keepalive));
      if (listenAutoResponse != null) {
        sb.append(COMMA);
        sb.append(listenAutoResponse ? "1" : "0");
        if (sendDataMode != null) {
          sb.append(COMMA);
          sb.append(String.valueOf(sendDataMode.value()));
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setListenAutoResponse(final Boolean listenAutoResponse) {
    this.listenAutoResponse = listenAutoResponse;
  }

  public void setSendDataMode(final DataMode sendDataMode) {
    this.sendDataMode = sendDataMode;
  }
}
