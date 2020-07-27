package com.github.pmoerenhout.atcommander.module.quectel.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class ConfigureParametersTcpipContextCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_QUECTEL_CONFIGURE_PARAMETERS_TCPIP_CONTEXT = "+QICSGP";

  private int contextId;
  private Integer contextType;
  private String apn;
  private String username;
  private String password;
  private String authentication;

  public ConfigureParametersTcpipContextCommand(final AtCommander atCommander, final int contextId) {
    super(COMMAND_QUECTEL_CONFIGURE_PARAMETERS_TCPIP_CONTEXT, atCommander);
    this.contextId = contextId;
  }

  public ConfigureParametersTcpipContextCommand(final AtCommander atCommander, final int contextId, final int contextType, final String apn) {
    super(COMMAND_QUECTEL_CONFIGURE_PARAMETERS_TCPIP_CONTEXT, atCommander);
    this.contextId = contextId;
    this.contextType = contextType;
    this.apn = apn;
  }

  public ConfigureParametersTcpipContextCommand(final AtCommander atCommander, final int contextId, final int contextType, final String apn,
                                                final String username, final String password) {
    super(COMMAND_QUECTEL_CONFIGURE_PARAMETERS_TCPIP_CONTEXT, atCommander);
    this.contextId = contextId;
    this.contextType = contextType;
    this.apn = apn;
    this.username = username;
    this.password = password;
  }

  public ConfigureParametersTcpipContextCommand(final AtCommander atCommander, final int contextId, final int contextType, final String apn,
                                                final String username, final String password, final String authentication) {
    super(COMMAND_QUECTEL_CONFIGURE_PARAMETERS_TCPIP_CONTEXT, atCommander);
    this.contextId = contextId;
    this.contextType = contextType;
    this.apn = apn;
    this.username = username;
    this.password = password;
    this.authentication = authentication;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_QUECTEL_CONFIGURE_PARAMETERS_TCPIP_CONTEXT);
      sb.append(EQUAL);
      sb.append(contextId);
      if (contextType != null) {
        sb.append(COMMA);
        sb.append(contextType);
        sb.append(COMMA);
        sb.append(DOUBLE_QUOTE);
        sb.append(apn);
        sb.append(DOUBLE_QUOTE);
        if (username != null) {
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          sb.append(username);
          sb.append(DOUBLE_QUOTE);
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          sb.append(password);
          sb.append(DOUBLE_QUOTE);
          if (authentication != null) {
            sb.append(COMMA);
            sb.append(authentication);
          }
        }
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
