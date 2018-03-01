package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;

public class SocketConfigurationExtended2Command extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SCFGEXT2 = "#SCFGEXT2";

  private int socketId;
  private int bufferStart;
  private int abortConnAttempt;
  private int sringLen;
  private int sringTo;
  private int noCarrierMode;

  public SocketConfigurationExtended2Command(final AtCommander atCommander, final int socketId, final int bufferStart,
                                             final int abortConnAttempt, final int sringLen, final int sringTo,
                                             final int noCarrierMode) {
    super(COMMAND_SCFGEXT2, atCommander);
    this.socketId = socketId;
    this.bufferStart = bufferStart;
    this.abortConnAttempt = abortConnAttempt;
    this.sringLen = sringLen;
    this.sringTo = sringTo;
    this.noCarrierMode = noCarrierMode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SCFGEXT2);
      sb.append(EQUAL);
      sb.append(String.valueOf(socketId));
      sb.append(COMMA);
      sb.append(String.valueOf(bufferStart));
      sb.append(COMMA);
      sb.append(String.valueOf(abortConnAttempt));
      sb.append(COMMA);
      sb.append(String.valueOf(sringLen));
      sb.append(COMMA);
      sb.append(String.valueOf(sringTo));
      sb.append(COMMA);
      sb.append(String.valueOf(noCarrierMode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}