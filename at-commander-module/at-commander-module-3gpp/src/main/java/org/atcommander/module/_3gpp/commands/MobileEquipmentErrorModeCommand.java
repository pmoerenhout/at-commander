package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.commands.TestResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class MobileEquipmentErrorModeCommand extends BaseCommand implements Command<EmptyResponse> {

  public static final int DISABLE = 0;
  public static final int ENABLE = 1;
  private static final String COMMAND_CMEEMODE = "#CMEEMODE";

  private int mode;

  public MobileEquipmentErrorModeCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CMEEMODE, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CMEEMODE);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public TestResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CMEEMODE);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new TestResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
