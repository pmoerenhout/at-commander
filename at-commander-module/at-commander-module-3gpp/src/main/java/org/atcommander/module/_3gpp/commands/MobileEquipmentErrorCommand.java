package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.commands.TestResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class MobileEquipmentErrorCommand extends BaseCommand implements Command<EmptyResponse> {

  public static final int DISABLED = 0;
  public static final int ENABLED_NUMERIC = 1;
  public static final int ENABLED_VERBOSE = 2;

  private static final String COMMAND_CMEE = "+CMEE";

  private int mode;

  public MobileEquipmentErrorCommand(final AtCommander atCommander, final int mode) {
    super(COMMAND_CMEE, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CMEE);
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
      sb.append(COMMAND_CMEE);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new TestResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
