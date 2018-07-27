package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class MessageTerminatingIndicationResponse extends BaseResponse implements Response, UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CMTI: \"(.*)\",(\\d*)$");
  // +CMTI: "SM",3
  private static final Pattern PATTERN = Pattern.compile("^\\+CMTI: \"(.*)\",(\\d*)$");

  private String storage;
  private int index;

  public MessageTerminatingIndicationResponse() {
  }

  public MessageTerminatingIndicationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  private void parse(final String line) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(line);
    if (m.find()) {
      storage = m.group(1);
      index = Integer.parseUnsignedInt(m.group(2));
      return;
    }
    throw createParseException(line);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        storage = m.group(1);
        index = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getStorage() {
    return storage;
  }

  public int getIndex() {
    return index;
  }
}
