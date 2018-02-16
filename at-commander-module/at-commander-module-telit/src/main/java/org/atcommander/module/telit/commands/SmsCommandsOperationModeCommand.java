package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SmsCommandsOperationModeCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SMSMODE = "#SMSMODE";

  private int value;

  public SmsCommandsOperationModeCommand(final AtCommander atCommander) {
    super(COMMAND_SMSMODE, atCommander);
  }

  public SmsCommandsOperationModeCommand(final AtCommander atCommander, final int value) {
    super(COMMAND_SMSMODE, atCommander);
    this.value = value;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SMSMODE);
      sb.append(EQUAL);
      sb.append(String.valueOf(value));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SmsCommandsOperationResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SMSMODE);
      sb.append(QUERY);
      return new SmsCommandsOperationResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
