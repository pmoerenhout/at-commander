package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.types.Message;
import com.github.pmoerenhout.atcommander.module._3gpp.types.PduMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.TextMessage;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class ReadMessageResponse extends BaseResponse implements Response {

  // +CMGR: "REC UNREAD","+31614240689","","18/09/28,16:34:55+08",145,4,0,0,"+31640191919",145,33
  // +CMGR: <stat>,<oa>,,<scts> [,<tooa>,<fo>,<pid>,<dcs>,<sca>,<tosca>,<length>]<CR><LF><data>
  // +CMGR: "REC UNREAD","+31614240689","","18/10/05,11:51:25+08",145,4,0,0,"+31640191919",145,5
  // +CMGR: <stat>,<oa>,<alpha>,<scts>[,<tooa>,<fo>,<pid>,<dcs>,<sca>,<tosca>,<length>]<CR><LF><data>
  private static final Pattern PATTERN_TEXT = Pattern.compile("^\\+CMGR: \"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\"");
  private static final Pattern PATTERN_PDU = Pattern.compile("^\\+CMGR: (\\d),(\"(.*)\")?,(\\d*)$");
  private static final String CMGR = "+CMGR: ";

  private MessageMode messageMode;
  private Message message;

  public ReadMessageResponse(final MessageMode messageMode, final AtResponse s) {
    this.messageMode = messageMode;
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 2) {
      final String line = informationalText.get(0);
      if (messageMode == MessageMode.TEXT) {
        final Matcher matcherText = PATTERN_TEXT.matcher(line);
        if (matcherText.find()) {
          final String text = informationalText.get(1);
          final String[] tokens = Util.tokenize(StringUtils.removeStart(line, CMGR));
          System.out.println("Tokens " + tokens.length);
          final MessageStatus status = MessageStatus.fromString(tokens[0]);
          final String oada = tokens[1];
          final String alpha = StringUtils.defaultString(tokens[2]);
          final ZonedDateTime scts = Util.getTimestamp(tokens[3]);
          if (tokens.length == 4) {
            message = new TextMessage(status, oada, alpha, scts, text);
            return;
          } else if (tokens.length == 11) {
            // +CSDH is set
            final int toOa = Integer.parseInt(tokens[4]);
            final int firstOctet = Integer.parseInt(tokens[5]);
            final int pid = Integer.parseInt(tokens[6]);
            final int dcs = Integer.parseInt(tokens[7]);
            final String sca = tokens[8];
            final int toSca = Integer.parseInt(tokens[9]);
            final int length = Integer.parseInt(tokens[10]);
            message = new TextMessage(status, oada, alpha, scts, toOa,firstOctet, pid, dcs, sca, toSca, length, text);
            return;
          }
          message = new TextMessage(status, oada, alpha, scts, text);
          return;
        }
      } else if (messageMode == MessageMode.PDU) {
        final Matcher matcherPdu = PATTERN_PDU.matcher(line);
        if (matcherPdu.find()) {
          final MessageStatus status = MessageStatus.fromInt(Integer.valueOf(matcherPdu.group(1)));
          final String alpha = matcherPdu.group(3);
          final int length = Integer.valueOf(matcherPdu.group(4));
          final String pdu = informationalText.get(1);
          message = new PduMessage(status, alpha, length, pdu);
          return;
        }
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public Message getMessage() {
    return message;
  }

}
