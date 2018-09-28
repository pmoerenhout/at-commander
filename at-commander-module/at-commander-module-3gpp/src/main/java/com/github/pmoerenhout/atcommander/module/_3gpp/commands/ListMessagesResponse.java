package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.types.IndexMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.IndexPduMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.IndexTextMessage;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class ListMessagesResponse extends BaseResponse implements Response {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy,HH:mm:ssX");
  private static final String CMGL = "+CMGL: ";

  // +CMGL: 1,0,"",23
  // +CMGL: 1,"REC READ","+31614240689","","13/12/17,02:34:52+04"
  // Text

//  if text mode (+CMGF=1), command successful and SMS-SUBMITs and/or SMS-
//  DELIVERs:
//      +CMGL: <index>,<stat>,<oa/da>,[<alpha>],[<scts>]
//      [,<tooa/toda>,
//<length>]
//<CR><LF><data>[<CR><LF>
//+CMGL: <index>,<stat>,<da/oa>,[<alpha>],[<scts>]
//      [,<tooa/toda>,
//<length>]
//<CR><LF><data>[...]]
//      if text mode (
//+CMGF=1
//  ), command successful and SMS-STATUS-REPORTs:
//      +CMGL: <index>,<stat>,<fo>,<mr>,[<ra>],[<tora>],<scts>,<dt>,<st>
//[<CR><LF>
//+CMGL: <index>,<stat>,<fo>,<mr>,[<ra>],[<tora>],<scts>,<dt>,<st>
//[...]]
//    if text mode (
//+CMGF=1
//  ), command successful and SMS-COMMANDs:
//      +CMGL: <index>,<stat>,<fo>,<ct>[<CR><LF>
//+CMGL: <index>,<stat>,<fo>,<ct>[...]]
//      if text mode (
//+CMGF=1
//  ), command successful and CBM storage:
//      +CMGL: <index>,<stat>,<sn>,<mid>,<page>,<pages>
//<CR><LF><data>[<CR><LF>
//+CMGL: <index>,<stat>,<sn>,<mid>,<page>,<pages>
//<CR><LF><data>[...]]

  private static final Pattern PATTERN_PDU = Pattern.compile("^\\+CMGL: (\\d*),(\\d*)(,\"(.*)\")?,(\\d*)$");
  // +CMGL: <index>,<stat>,<oa/da>,[<alpha>],[<scts>]
  private static final Pattern PATTERN_TEXT = Pattern.compile("^\\+CMGL: (\\d*),\"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\"$");
  private List<IndexMessage> indexMessages;

  public ListMessagesResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final ArrayList<IndexMessage> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (int i = 0; i < informationalText.size(); i += 2) {
      final String line = informationalText.get(i);
      final Matcher matcherPdu = PATTERN_PDU.matcher(line);
      if (matcherPdu.find()) {
        final String[] tokens = Util.tokenize(StringUtils.stripStart(line, CMGL));
        final int index = Integer.parseInt(tokens[0]);
        final MessageStatus status = MessageStatus.fromInt(Integer.parseInt(tokens[1]));
        if (tokens.length == 3) {
          final int length = Integer.parseInt(tokens[2]);
          final String pdu = informationalText.get(i + 1);
          arrayList.add(new IndexPduMessage(index, status, length, pdu));
        } else {
          final String alpha = StringUtils.defaultString(tokens[2],"");
          final int length = Integer.parseInt(tokens[3]);
          final String pdu = informationalText.get(i + 1);
          arrayList.add(new IndexPduMessage(index, status, alpha, length, pdu));
        }
        continue;
      }
      final Matcher matcherText = PATTERN_TEXT.matcher(line);
      if (matcherText.find()) {
        final int index = Integer.parseInt(matcherText.group(1));
        final MessageStatus status = MessageStatus.fromString(matcherText.group(2));
        final String oada = matcherText.group(3);
        final String alpha = matcherText.group(4);
        final ZonedDateTime scts = ZonedDateTime.parse(matcherText.group(5), DATE_TIME_FORMATTER);
        final String text = informationalText.get(i + 1);
        arrayList.add(new IndexTextMessage(index, status, oada, alpha, scts, text));
        continue;
      }
      throw createParseException(line);
    }
    indexMessages = arrayList;
  }

  public List<IndexMessage> getIndexMessages() {
    return indexMessages;
  }

}
