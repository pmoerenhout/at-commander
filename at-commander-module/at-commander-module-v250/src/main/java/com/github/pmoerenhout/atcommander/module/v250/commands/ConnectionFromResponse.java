package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class ConnectionFromResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CONN FROM: (.*)$");

  private String address;

  public ConnectionFromResponse() {
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  public void parse(final String line) {
    final Matcher m1 = UNSOLICITED_PATTERN.matcher(line);
    if (m1.find()) {
      address = m1.group(1);
      return;
    }
    throw createParseException(line);
  }

  public String getAddress() {
    return address;
  }
}