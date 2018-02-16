package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class MoreMessagesToSendCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_MMS = "+CMMS";

  private int mode;

  public MoreMessagesToSendCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_MMS, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MMS);
      sb.append(EQUAL);
      sb.append(Integer.toString(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public MoreMessagesToSendResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MMS);
      sb.append(QUERY);
      return new MoreMessagesToSendResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}