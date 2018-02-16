package org.atcommander.module.telit.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.basic.commands.BaseResponse;

public class NoCarrierResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN1 = Pattern.compile("^NO CARRIER:([1-6])$");
  public static final Pattern UNSOLICITED_PATTERN2 = Pattern.compile("^NO CARRIER:([1-6]),(\\d*)$");

  private Integer socketId;
  private Integer cause;

  public NoCarrierResponse(final String line) {
    parse(line);
  }

  public void parse(final String line) {
    final Matcher m2 = UNSOLICITED_PATTERN1.matcher(line);
    if (m2.find()) {
      socketId = Integer.parseInt(m2.group(1));
      return;
    }

    final Matcher m3 = UNSOLICITED_PATTERN2.matcher(line);
    if (m3.find()) {
      socketId = Integer.parseInt(m3.group(1));
      cause = Integer.parseInt(m3.group(2));
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
