package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class RingResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern PATTERN = Pattern.compile("^RING$");

  public RingResponse() {
  }

  public RingResponse(final String line) {
    parse(line);
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  public void parse(final String line) {
    final Matcher m1 = PATTERN.matcher(line);
    if (m1.find()) {
      return;
    }
    throw createParseException(line);
  }
}

