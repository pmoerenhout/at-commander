package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class RestoreSettingsCommand extends BaseCommand implements Command<BaseResponse> {

  static private final String COMMAND_RESTORE_SETTINGS = "+CRES";

  private int profile;

  public RestoreSettingsCommand(final AtCommander atCommander, final int profile) {
    super(COMMAND_RESTORE_SETTINGS, atCommander);
    this.profile = profile;
  }

  public SelectMessageServiceResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_RESTORE_SETTINGS);
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
      sb.append(COMMAND_RESTORE_SETTINGS);
      sb.append(QUERY);
      return new SelectMessageServiceResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}