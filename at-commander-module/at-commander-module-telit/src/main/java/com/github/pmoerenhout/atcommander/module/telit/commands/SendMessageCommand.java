package com.github.pmoerenhout.atcommander.module.telit.commands;


import java.nio.charset.Charset;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.EtsiUtil;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;

public class SendMessageCommand extends BaseCommand implements Command<BaseResponse> {

  private static final String COMMAND_MESSAGE_SEND = "#CMGS";
  private MessageMode messageMode;
  private String characterSet;
  private String pdu;
  private int length;

  private String address;
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
          // LOG.debug("PDU length {} CMGS length {}", pdu.length(), length);
          if (length < 7 || length > 164) {
            throw new IllegalArgumentException("The PDU length must be in range 7..164");
          }
          sb.append(Integer.toString(length));
          sb.append(COMMA);
          sb.append(pdu);
          final SendMessageResponse resp = new SendMessageResponse(super.execute(sb.toString()));
          return resp;

        case TEXT:
          // LOG.debug("TEXT ({}) '{}'", text.length(), ModemUtil.onlyPrintable(text.getBytes()));
          sb.append(address);
          sb.append(COMMA);
          sb.append(DOUBLE_QUOTE);
          switch (characterSet) {
            case "GSM":
            case "IRA":
            case "HEX":
              sb.append(text);
              break;
            case "UCS2":
              final Charset charset = EtsiUtil.toJavaCharset(characterSet);
              sb.append(Util.bytesToHexString(text.getBytes(charset)).toUpperCase());
              break;
            default:
              throw new IllegalArgumentException("Unhandled characterset " + characterSet);
          }
          sb.append(DOUBLE_QUOTE);
          return new SendMessageResponse(super.execute(sb.toString()));

        default:
          throw new IllegalArgumentException("Invalid message mode: " + messageMode);
      }
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

  public void setText(final String text) {
    this.text = text;
  }

}
