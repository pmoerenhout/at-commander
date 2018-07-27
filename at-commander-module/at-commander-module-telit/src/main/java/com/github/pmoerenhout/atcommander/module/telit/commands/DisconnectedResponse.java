package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class DisconnectedResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^DISCONNECTED");

  public DisconnectedResponse() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      parse(lines.get(0));
      return;
    }
    throw createParseException(lines);
  }

  private void parse(final String line) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(line);
    if (m.find()) {
      return;
    }
    throw createParseException(line);
  }
}
