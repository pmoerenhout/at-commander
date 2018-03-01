package com.github.pmoerenhout.atcommander.module.telit.commands;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class HttpQueryCommand extends BaseCommand implements Command<BaseResponse> {

  // #HTTPQRY: (0-2),(0-2),150,100

  private static final String COMMAND_HTTPQRY = "#HTTPQRY";

  private int profileId;
  private int command;
  private String resource;
  private String extraHeader;

  public HttpQueryCommand(final AtCommander atCommander, final int profileId, final int command,
                          final String resource) {
    super(COMMAND_HTTPQRY, atCommander);
    this.profileId = profileId;
    this.command = command;
    this.resource = resource;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_HTTPQRY);
      sb.append(EQUAL);
      sb.append(String.valueOf(profileId));
      sb.append(COMMA);
      sb.append(String.valueOf(command));
      sb.append(COMMA);
      sb.append(DOUBLE_QUOTE);
      sb.append(resource);
      sb.append(DOUBLE_QUOTE);
      if (extraHeader != null) {
        sb.append(COMMA);
        sb.append(DOUBLE_QUOTE);
        sb.append(String.valueOf(extraHeader));
        sb.append(DOUBLE_QUOTE);
      }
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public void setExtraHeader(final String extraHeader) {
    this.extraHeader = extraHeader;
  }
}
