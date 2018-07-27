package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.common.Util;

public class NoCarrierResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^NO CARRIER:(.*)$");

  private Integer socketId;
  private Integer cause;

  public NoCarrierResponse() {
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  public void parse(final String line) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(line);
    if (m.find()) {
      final String[] tokens = Util.tokenize(m.group(1));
      socketId = Integer.parseInt(tokens[0]);
      if (tokens.length == 2) {
        cause = Integer.parseInt(tokens[1]);
      }
      if (tokens.length > 2) {
        throw createParseException(line);
      }
      return;
    }
    throw createParseException(line);
  }

  public Integer getSocketId() {
    return socketId;
  }

  public Integer getCause() {
    return cause;
  }
}
