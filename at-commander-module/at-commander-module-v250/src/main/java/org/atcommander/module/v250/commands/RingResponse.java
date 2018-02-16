package org.atcommander.module.v250.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.basic.commands.BaseResponse;

public class RingResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern PATTERN = Pattern.compile("^RING$");

  public RingResponse() {
  }

  public RingResponse(final String line) {
    parse(line);
  }

  public void parse(final String line) {
    final Matcher m1 = PATTERN.matcher(line);
    if (m1.find()) {
      return;
    }
    throw createParseException(line);
  }
}

