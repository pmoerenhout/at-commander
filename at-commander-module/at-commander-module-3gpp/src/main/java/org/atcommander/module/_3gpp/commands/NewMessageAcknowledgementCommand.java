package org.atcommander.module._3gpp.commands;

import java.nio.charset.StandardCharsets;

import org.atcommander.AtCommander;
import org.atcommander.AtResponse;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.EmptyResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewMessageAcknowledgementCommand extends BaseCommand implements Command<BaseResponse> {

  private static final Logger LOG = LoggerFactory.getLogger(SendMessageCommand.class);

  private static final String COMMAND_NEW_MESSAGE_ACKNOWLEDGEMENT= "+CNMA";

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
      sb.append(Integer.toString(type));
      if (length != null) {
        sb.append(COMMA);
        sb.append(Integer.toString(length));
        }
      }
      final AtResponse s = super.execute(sb.toString());
      for (final String t : s.getInformationalText()) {
        LOG.debug("Received line {}", t);
      }
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        LOG.info("Interrupted");
      }
      super.writeBytes(pdu.getBytes(StandardCharsets.US_ASCII));

      //LOG.debug("Now sending Ctrl-Z");
      return new EmptyResponse(super.execute(CTRLZ, 60000));
    } finally {
      available.release();
    }
  }

}