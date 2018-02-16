package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.module.v250.enums.MessageMode;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class MessageFormatCommand extends BaseCommand implements Command<EmptyResponse> {

  static final String COMMAND_MGF = "+CMGF";

  private MessageMode mode;

  public MessageFormatCommand(final AtCommander atCommander, final MessageMode mode) {
    super(COMMAND_MGF, atCommander);
    this.mode = mode;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MGF);
      sb.append(EQUAL);
      sb.append(String.valueOf(mode.value()));
      return new EmptyResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

  public MessageFormatResponse read() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MGF);
      sb.append(QUERY);
      return new MessageFormatResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }
}
