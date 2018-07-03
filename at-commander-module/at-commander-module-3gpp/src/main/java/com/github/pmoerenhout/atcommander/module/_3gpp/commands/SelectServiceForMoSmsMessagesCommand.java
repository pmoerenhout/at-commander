package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

public class SelectServiceForMoSmsMessagesCommand extends BaseCommand implements Command<EmptyResponse> {

  private static final String COMMAND_CGSMS = "+CGSMS";

  private int service;

  public SelectServiceForMoSmsMessagesCommand(final AtCommander atCommander) {
    super(COMMAND_CGSMS, atCommander);
  }

  public SelectServiceForMoSmsMessagesCommand(final AtCommander atCommander, final int service) {
    super(COMMAND_CGSMS, atCommander);
    this.service = service;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGSMS);
      sb.append(EQUAL);
      sb.append(Integer.toString(service));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SelectServiceForMoSmsMessagesResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGSMS);
      sb.append(QUERY);
      return new SelectServiceForMoSmsMessagesResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public SelectServiceForMoSmsMessagesResponse test() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_CGSMS);
      sb.append(EQUAL);
      sb.append(QUERY);
      return new SelectServiceForMoSmsMessagesResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
