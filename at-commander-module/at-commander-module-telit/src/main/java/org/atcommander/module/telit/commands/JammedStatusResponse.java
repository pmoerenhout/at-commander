package org.atcommander.module.telit.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.basic.exceptions.ParseException;

public class JammedStatusResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#JDR: (.*)$");

  private String status;

  public JammedStatusResponse(final String s) {
    parse(s);
  }

  public void parse(final String response) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(response);
    if (m.find()) {
      status = m.group(1);
      return;
    }
    throw new ParseException("Could not parse response: " + response);
  }

  public String getStatus() {
    return status;
  }

}
