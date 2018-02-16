package org.atcommander.module._3gpp.commands;


import org.atcommander.AtCommander;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.module.v250.enums.MessageMode;
import org.atcommander.module.v250.enums.MessageStatus;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;

public class SendListMessagesCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_LIST_MESSAGES = "+CMGL";
  private MessageMode messageMode;
  private MessageStatus status;

  public SendListMessagesCommand(final AtCommander atCommander, final MessageMode messageMode, final MessageStatus status) {
    super(COMMAND_LIST_MESSAGES, atCommander);
    this.messageMode = messageMode;
    this.status = status;
  }

  public ListMessagesResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_LIST_MESSAGES);
      sb.append(EQUAL);
      switch (messageMode) {
        case PDU:
          sb.append(status.pduValue());
          break;
        case TEXT:
          sb.append(status.textValue());
          break;
        default:
          throw new IllegalArgumentException("Unknown messagemode");
      }
      return new ListMessagesResponse(super.execute(sb.toString()));
    } finally {
      available.release();
    }
  }

}
