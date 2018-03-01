package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class NitzResponse extends BaseResponse implements Response, UnsolicitedResponse {

  /* #NITZ: 15/03/27,16:37:22+04,0 */
  public final static Pattern UNSOLICITED_PATTERN = Pattern
      .compile("^#NITZ: ([0-9]{2})/([0-9]{2})/([0-9]{2}),([0-9]{2}):([0-9]{2}):([0-9]{2})([-+][0-9]{2}),([0-9])$");

  private final static Pattern PATTERN = Pattern.compile("^#NITZ: ([0-9]{2})/([0-9]{2})/([0-9]{2}),([0-9]{2}):([0-9]{2}):([0-9]{2})([-+][0-9]{2}),([0-9])$");
  private final static Pattern PATTERN2 = Pattern.compile("^#NITZ: (\\d),(\\d)$");

  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;
  private int second;
  private int timezone;
  private int dst;

  public NitzResponse(final String s) {
    parse(s);
  }

  public NitzResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final String response) {
    final Matcher m = PATTERN.matcher(response);
    if (m.find()) {
      year = Integer.parseInt(m.group(1));
      month = Integer.parseInt(m.group(2));
      day = Integer.parseInt(m.group(3));
      hour = Integer.parseInt(m.group(4));
      minute = Integer.parseInt(m.group(5));
      second = Integer.parseInt(m.group(6));
      timezone = Integer.parseInt(m.group(7));
      dst = Integer.parseInt(m.group(8));
      return;
    }
    throw new ParseException("Could not parse response: " + response);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN2.matcher(line);
      if (m.find()) {
        final int value = Integer.parseInt(m.group(1));
        final int mode = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public OffsetDateTime getOffsetDateTime() {
    final LocalDateTime localDate = LocalDateTime.of(2000 + year, month, day, hour, minute, second);
    return OffsetDateTime.of(localDate, ZoneOffset.ofHours(timezone));
  }

  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public int getSecond() {
    return second;
  }

  public int getTimezone() {
    return timezone;
  }

  public int getDst() {
    return dst;
  }
}
