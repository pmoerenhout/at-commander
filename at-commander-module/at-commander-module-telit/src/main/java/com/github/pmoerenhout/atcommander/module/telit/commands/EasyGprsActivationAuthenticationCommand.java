package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.Authentication;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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