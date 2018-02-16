package org.atcommander.module._3gpp.commands;


import java.nio.charset.Charset;

import org.atcommander.AtCommander;
import org.atcommander.AtResponse;
import org.atcommander.Command;
import org.atcommander.api.SerialException;
import org.atcommander.basic.commands.BaseCommand;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.exceptions.ResponseException;
import org.atcommander.basic.exceptions.TimeoutException;
import org.atcommander.common.Util;
import org.atcommander.module._3gpp.EtsiUtil;
import org.atcommander.module.v250.enums.MessageMode;
import org.atcommander.module.v250.enums.MessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WriteMessageToMemoryCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_WRITE_MESSAGE_TO_MEMORY = "+CMGW";
  private static final Logger LOG = LoggerFactory.getLogger(WriteMessageToMemoryCommand.class);
  private MessageMode messageMode;
  private String characterSet;
  private String pdu;
  private int length;
  private MessageStatus messageStatus;

  private String address;
  private Integer typeOfAddress;
  private String text;

  public WriteMessageToMemoryCommand(final AtCommander atCommander, final MessageMode messageMode) {
    super(COMMAND_WRITE_MESSAGE_TO_MEMORY, atCommander);
    this.messageMode = messageMode;
  }

  public WriteMessageToMemoryCommand(final AtCommander atCommander, final MessageMode messageMode, final String characterSet) {
    super(COMMAND_WRITE_MESSAGE_TO_MEMORY, atCommander);
    this.messageMode = messageMode;
    this.characterSet = characterSet;
  }

  public WriteMessageToMemoryResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_WRITE_MESSAGE_TO_MEMORY);
      sb.append(EQUAL);
      switch (messageMode) {
        case PDU:
          LOG.debug("PDU length {} CMGW length {}", pdu.length(), length);
          sb.append(Integer.toString(length));
          if (messageStatus != null) {
            sb.append(COMMA);
            sb.append(Integer.toString(messageStatus.pduValue()));
          }
          final AtResponse s = super.execute(sb.toString());
          s.getInformationalText().forEach(line -> LOG.debug("Received line {}", line));
          super.writeBytes(pdu.getBytes());
          break;

        case TEXT:
          LOG.debug("TEXT ({}) '{}'", text.length(), Util.onlyPrintable(text.getBytes()));
          sb.append(address);
          if (typeOfAddress != null) {
            sb.append(COMMA);
            sb.append(String.valueOf(typeOfAddress));
            if (messageStatus != null) {
              sb.append(COMMA);
              sb.append(messageStatus.textValue());
            }
          }
          final AtResponse s2 = super.execute(sb.toString());
          s2.getInformationalText().forEach(line -> LOG.debug("Received line {}", line));
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            LOG.info("Interrupted");
          }
          final Charset charset = EtsiUtil.toJavaCharset(characterSet);
          LOG.info("Using charset {} to {} ({})", characterSet, charset.name(), charset.displayName());
          super.writeBytes(text.getBytes(charset));
          break;
      }

      //LOG.debug("Now sending Ctrl-Z");
      final WriteMessageToMemoryResponse resp = new WriteMessageToMemoryResponse(super.execute(CTRLZ, 60000));
      return resp;
    } finally {
      available.release();
    }
  }

  public String getPdu() {
    return pdu;
  }

  public void setPdu(final String pdu) {
    this.pdu = pdu;
  }

  public int getLength() {
    return length;
  }

  public void setLength(final int length) {
    this.length = length;
  }

  public void setAddress(final String address) {
    this.address = address;
  }

  public void setTypeOfAddress(final int typeOfAddress) {
    this.typeOfAddress = typeOfAddress;
  }

  public void setText(final String text) {
    this.text = text;
  }
}
