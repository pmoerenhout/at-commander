package org.atcommander.module.telit.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.basic.commands.BaseResponse;

public class RingingResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^RINGING$");

  public RingingResponse(final String s) {
    parse(s);
  }

  public void parse(final String line) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(line);
    if (m.find()) {
      return;
    }
    throw createParseException(line);
  }
}
