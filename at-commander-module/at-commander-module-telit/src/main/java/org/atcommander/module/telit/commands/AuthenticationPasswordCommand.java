package org.atcommander.module.telit.commands;

import org.apache.commons.lang3.StringUtils;
import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class AuthenticationPasswordCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_PASSW = "#PASSW";

  private String password;

  public AuthenticationPasswordCommand(final AtCommander atCommander, final String password) {
    super(COMMAND_PASSW, atCommander);
    this.password = password;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_PASSW);
      sb.append(EQUAL);
      if (StringUtils.isNotBlank(password)) {
        sb.append(password);
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
