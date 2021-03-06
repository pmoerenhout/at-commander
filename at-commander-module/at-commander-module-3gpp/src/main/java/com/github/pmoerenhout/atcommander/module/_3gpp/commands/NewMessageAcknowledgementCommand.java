package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.nio.charset.StandardCharsets;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewMessageAcknowledgementCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_NEW_MESSAGE_ACKNOWLEDGEMENT = "+CNMA";

  private Integer type;
  private Integer length;
  private String pdu;

  public NewMessageAcknowledgementCommand(final AtCommander atCommander) {
    super(COMMAND_NEW_MESSAGE_ACKNOWLEDGEMENT, atCommander);
  }

  public NewMessageAcknowledgementCommand(final AtCommander atCommander, final int type) {
    super(COMMAND_NEW_MESSAGE_ACKNOWLEDGEMENT, atCommander);
    this.type = type;
  }

  public NewMessageAcknowledgementCommand(final AtCommander atCommander, final int type, final int length, final String pdu) {
    super(COMMAND_NEW_MESSAGE_ACKNOWLEDGEMENT, atCommander);
    this.type = type;
    this.length = length;
    this.pdu = pdu;
  }

  public EmptyResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_NEW_MESSAGE_ACKNOWLEDGEMENT);
      if (type != null) {
        sb.append(EQUAL);
        sb.append(type);
        if (length != null) {
          sb.append(COMMA);
          sb.append(length);
        }
      }
      final AtResponse s = super.execute(sb.toString());
      for (final String t : s.getInformationalText()) {
        log.debug("Received line {}", t);
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        log.info("Interrupted");
      }
      super.writeBytes(pdu.getBytes(StandardCharsets.US_ASCII));

      //LOG.debug("Now sending Ctrl-Z");
      return new EmptyResponse(super.execute(CTRLZ, 60000));
    } finally {
      available.release();
    }
  }

}