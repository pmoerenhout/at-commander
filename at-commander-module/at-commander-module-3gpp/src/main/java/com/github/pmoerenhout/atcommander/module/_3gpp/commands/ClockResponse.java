package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ClockResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CCLK: \"(.*)\"$");

  // 18/07/28,22:27:00+08
  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yy/MM/dd,HH:mm:ss");

  private String time;

  public ClockResponse(final AtResponse s) {
    this.parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        time = m.group(1);
        return;
      }
      throw createParseException(line);
    }
  }

  public String getTime() {
    return time;
  }

  public Temporal getTemporal() {
    final LocalDateTime local = LocalDateTime.parse(time.substring(0, 17), DATE_TIME_FORMATTER);
    if (time.length() >= 20) {
      final int offsetSeconds = Integer.parseInt(time.substring(17, 20)) * 900;
      return ZonedDateTime.of(local, ZoneOffset.ofTotalSeconds(offsetSeconds));
    }
    return local;
  }
}
