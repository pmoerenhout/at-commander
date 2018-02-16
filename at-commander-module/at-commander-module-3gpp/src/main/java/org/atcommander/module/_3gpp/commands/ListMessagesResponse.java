package org.atcommander.module._3gpp.commands;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.module._3gpp.types.ListMessage;
import org.atcommander.module.v250.enums.MessageStatus;

public class ListMessagesResponse extends BaseResponse implements Response {

  final static private DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yy,HH:mm:ssX");

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

  private static final Pattern PATTERN_PDU = Pattern.compile("^\\+CMGL: (\\d*),(\\d*),\"(.*)\",(\\d*)$");
  // +CMGL: <index>,<stat>,<oa/da>,[<alpha>],[<scts>]
  private static final Pattern PATTERN_TEXT = Pattern.compile("^\\+CMGL: (\\d*),\"(.*)\",\"(.*)\",\"(.*)\",\"(.*)\"$");
  private List<ListMessage> listMessages;

  public ListMessagesResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final ArrayList<ListMessage> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (int i = 0; i < informationalText.size(); i += 2) {
      final String line = informationalText.get(i);
      final Matcher matcherPdu = PATTERN_PDU.matcher(line);
      final Matcher matcherText = PATTERN_TEXT.matcher(line);
      if (matcherPdu.find()) {
        final int cid = Integer.parseInt(matcherPdu.group(1));
        final MessageStatus status = MessageStatus.fromInt(Integer.parseInt(matcherPdu.group(2)));
        final String alpha = matcherPdu.group(3);
        final int length = Integer.parseInt(matcherPdu.group(4));
        final String pdu = informationalText.get(i + 1);
        arrayList.add(new ListMessage(cid, status, alpha, length, pdu));
      } else if (matcherText.find()) {
        final int cid = Integer.parseInt(matcherText.group(1));
        final MessageStatus status = MessageStatus.fromString(matcherText.group(2));
        final String oada = matcherText.group(3);
        final String alpha = matcherText.group(4);
        final ZonedDateTime scts = ZonedDateTime.parse(matcherText.group(5), DATE_TIME_FORMATTER);
        final String text = informationalText.get(i + 1);
        arrayList.add(new ListMessage(cid, status, oada, alpha, scts, text));
      } else {
        throw createParseException(line);
      }
    }
    listMessages = arrayList;
  }

  public List<ListMessage> getMessageList() {
    return listMessages;
  }

}
