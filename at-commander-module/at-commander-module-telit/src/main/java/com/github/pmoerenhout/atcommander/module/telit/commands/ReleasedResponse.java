package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class ReleasedResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^RELEASED$");

  public ReleasedResponse(final String s) {
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
