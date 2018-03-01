package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;

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
