package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;

public class ConnectionFromResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CONN FROM: (.*)$");

  private String address;

  public ConnectionFromResponse(final String line) {
    parse(line);
  }

  public void parse(final String line) {
    final Matcher m1 = UNSOLICITED_PATTERN.matcher(line);
    if (m1.find()) {
      address = m1.group(1);
      return;
    }
    throw new ParseException("Could not parse response: " + line);
  }

  public String getAddress() {
    return address;
  }
}