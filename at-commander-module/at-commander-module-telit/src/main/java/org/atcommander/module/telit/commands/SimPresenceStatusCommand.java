package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SimPresenceStatusCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SIMPR = "#SIMPR";

  private boolean notification;

  public SimPresenceStatusCommand(final AtCommander atCommander) {
    super(COMMAND_SIMPR, atCommander);
  }

  public SimPresenceStatusCommand(final AtCommander atCommander, final boolean notification) {
    super(COMMAND_SIMPR, atCommander);
    this.notification = notification;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SIMPR);
      sb.append(EQUAL);
      sb.append(oneOrZero(notification));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SimPresenceStatusResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SIMPR);
      sb.append(QUERY);
      return new SimPresenceStatusResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public EmptyResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SIMPR);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
