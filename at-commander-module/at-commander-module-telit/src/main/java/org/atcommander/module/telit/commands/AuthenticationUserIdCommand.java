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

public class AuthenticationUserIdCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_USERID = "#USERID";

  private String userid;

  public AuthenticationUserIdCommand(final AtCommander atCommander, final String userid) {
    super(COMMAND_USERID, atCommander);
    this.userid = userid;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_USERID);
      sb.append(EQUAL);
      if (StringUtils.isNotBlank(userid)) {
        sb.append(userid);
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
