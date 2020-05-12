package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class SendMessageResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CMGS: (\\d*)(,(.*))?$");
  private int reference;
  private String ackPdu;

  public SendMessageResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        reference = Integer.parseInt(m.group(1));
        if (m.groupCount() > 0) {
          ackPdu = m.group(3);
          return;
        }
        return;
      }
      throw createParseException(line);
    } else if (informationalText.size() == 2) {
      // SONY ERICCSON T610 send extra 0x00 character
      System.out.println("TTT: "  + informationalText.get(0));
      final String line = informationalText.get(1);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        reference = Integer.parseInt(m.group(1));
        if (m.groupCount() > 0) {
          ackPdu = m.group(3);
          return;
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getReference() {
    return reference;
  }

  public String getAckPdu() {
    return ackPdu;
  }
}
