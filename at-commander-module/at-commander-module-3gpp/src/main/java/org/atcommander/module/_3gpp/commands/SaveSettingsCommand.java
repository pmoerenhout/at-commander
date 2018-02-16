package org.atcommander.module._3gpp.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SaveSettingsCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_SAVE_SETTINGS = "+CSAS";

  private int profile;

  public SaveSettingsCommand(final AtCommander atCommander, final int service) {
    super(COMMAND_SAVE_SETTINGS, atCommander);
    this.profile = profile;
  }

  public SelectMessageServiceResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SAVE_SETTINGS);
      sb.append(EQUAL);
      sb.append(Integer.toString(profile));
      return new SelectMessageServiceResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SelectMessageServiceResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SAVE_SETTINGS);
      sb.append(QUERY);
      return new SelectMessageServiceResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}