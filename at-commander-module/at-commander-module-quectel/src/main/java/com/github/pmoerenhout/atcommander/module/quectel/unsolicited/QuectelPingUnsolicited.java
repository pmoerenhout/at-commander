package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class QuectelPingUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+QPING: (\\d*),\"(.*)\",(\\d*),(\\d*),(\\d*)$");

  private int result;
  private String ipAddress;
  private int bytes;
  private int time;
  private int ttl;

  public QuectelPingUnsolicited() {
  }

  @Override
  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  public void parse(final String response) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(response);
    if (m.find()) {
      result = Integer.parseInt(m.group(1));
      ipAddress = m.group(2);
      bytes = Integer.parseInt(m.group(3));
      time = Integer.parseInt(m.group(4));
      ttl = Integer.parseInt(m.group(5));
      return;
    }
    throw createParseException(response);
  }

  public int getResult() {
    return result;
  }

  public String getIpAddress() {
    return ipAddress;
  }

  public int getBytes() {
    return bytes;
  }

  public int getTime() {
    return time;
  }

  public int getTtl() {
    return ttl;
  }
}
