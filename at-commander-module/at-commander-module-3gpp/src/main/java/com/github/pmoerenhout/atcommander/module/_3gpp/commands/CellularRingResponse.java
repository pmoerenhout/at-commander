package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;

public class CellularRingResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CRING: (.*)$");

  private String type;

  public CellularRingResponse(final String s) {
    parse(s);
  }

  public void parse(final String response) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(response);
    if (m.find()) {
      type = m.group(1);
      return;
    }
    throw new ParseException("Could not parse response: " + response);
  }

  public String getType() {
    return type;
  }
}