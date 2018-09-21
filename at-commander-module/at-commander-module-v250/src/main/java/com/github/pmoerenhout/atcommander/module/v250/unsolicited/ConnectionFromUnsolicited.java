package com.github.pmoerenhout.atcommander.module.v250.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class ConnectionFromUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CONN FROM: (.*)$");

  private String address;

  public ConnectionFromUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m1 = UNSOLICITED_PATTERN.matcher(line);
      if (m1.find()) {
        address = m1.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getAddress() {
    return address;
  }
}