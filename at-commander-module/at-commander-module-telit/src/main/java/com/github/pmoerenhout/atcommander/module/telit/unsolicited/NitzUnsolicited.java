package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class NitzUnsolicited extends BaseResponse implements UnsolicitedResponse {

  /* #NITZ: 15/03/27,16:37:22 */
  /* #NITZ: 15/03/27,16:37:22+04 */
  /* #NITZ: 15/03/27,16:37:22+04,0 */
  public final static Pattern UNSOLICITED_PATTERN = Pattern
      .compile("^#NITZ: ([0-9]{2})/([0-9]{2})/([0-9]{2}),([0-9]{2}):([0-9]{2}):([0-9]{2})([-+][0-9]{2})?(,([0-9]))?$");

  private int day;
  private int month;
  private int year;
  private int hour;
  private int minute;
  private int second;
  private Integer timezone;
  private Integer dst;

  public NitzUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        year = Integer.parseInt(m.group(1));
        month = Integer.parseInt(m.group(2));
        day = Integer.parseInt(m.group(3));
        hour = Integer.parseInt(m.group(4));
        minute = Integer.parseInt(m.group(5));
        second = Integer.parseInt(m.group(6));
        timezone = getInteger(m.group(7));
        dst = getInteger(m.group(9));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public Temporal getDateTime() {
    final LocalDateTime localDate = LocalDateTime.of((year >= 90 ? 1900 : 2000) + year, month, day, hour, minute, second);
    if (timezone == null) {
      return localDate;
    }
    return OffsetDateTime.of(localDate, ZoneOffset.ofTotalSeconds((timezone != null ? timezone : 0) * 900 + ((dst != null ? dst : 0) * 3600)));
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

  public Integer getTimezone() {
    return timezone;
  }

  public Integer getDst() {
    return dst;
  }
}
