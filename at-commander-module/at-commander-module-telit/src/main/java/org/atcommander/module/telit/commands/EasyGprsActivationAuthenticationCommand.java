package org.atcommander.module.telit.commands;

import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.enums.Authentication;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class EasyGprsActivationAuthenticationCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_SGACTAUTH = "#SGACTAUTH";

  private Authentication authentication;

  public EasyGprsActivationAuthenticationCommand(final AtCommander atCommander, final Authentication authentication) {
    super(COMMAND_SGACTAUTH, atCommander);
    this.authentication = authentication;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_SGACTAUTH);
      sb.append(EQUAL);
      sb.append(String.valueOf(authentication.value()));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}