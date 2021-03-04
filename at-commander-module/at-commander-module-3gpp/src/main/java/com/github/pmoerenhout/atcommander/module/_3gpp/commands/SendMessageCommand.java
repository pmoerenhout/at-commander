package com.github.pmoerenhout.atcommander.module._3gpp.commands;


import java.nio.charset.Charset;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.EtsiUtil;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendMessageCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_MESSAGE_SEND = "+CMGS";

  private MessageMode messageMode;
  private String characterSet;
  private String pdu;
  private int length;

  private String address;
  private Integer typeOfAddress;
  private String text;

  public SendMessageCommand(final AtCommander atCommander, final MessageMode messageMode) {
    super(COMMAND_MESSAGE_SEND, atCommander);
    this.messageMode = messageMode;
  }

  public SendMessageCommand(final AtCommander atCommander, final MessageMode messageMode, final String characterSet) {
    super(COMMAND_MESSAGE_SEND, atCommander);
    this.messageMode = messageMode;
    this.characterSet = characterSet;
  }

  public SendMessageResponse set() throws SerialException, TimeoutException, ResponseException {
    available.acquireUninterruptibly();
    try {
      final StringBuilder sb = new StringBuilder(AT);
      sb.append(COMMAND_MESSAGE_SEND);
      sb.append(EQUAL);
      switch (messageMode) {
        case PDU:
          log.debug("PDU length {} CMGS length {}", pdu.length(), length);
          sb.append(length);

          final AtResponse s = super.execute(sb.toString());
          s.getInformationalText().forEach(line -> log.debug("Received line {}", line));
          super.writeBytes(pdu.getBytes());
          break;

        case TEXT:
          log.debug("TEXT ({}) '{}'", text.length(), Util.onlyPrintable(text.getBytes()));
          sb.append(address);
          if (typeOfAddress != null) {
            sb.append(COMMA);
            sb.append(typeOfAddress);
          }
          final AtResponse s2 = super.execute(sb.toString());
          s2.getInformationalText().forEach(line -> log.debug("Received line {}", line));
          try {
            Thread.sleep(1000);
          } catch (InterruptedException e) {
            log.info("Interrupted");
          }
          final Charset charset = EtsiUtil.toJavaCharset(characterSet);
          log.info("Using charset {} to {} ({})", characterSet, charset.name(), charset.displayName());
          super.writeBytes(text.getBytes(charset));
          break;
      }

      //LOG.debug("Now sending Ctrl-Z");
      final SendMessageResponse resp = new SendMessageResponse(super.execute(CTRLZ, 60000));
      log.debug("Reference is {}", resp.getReference());
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
