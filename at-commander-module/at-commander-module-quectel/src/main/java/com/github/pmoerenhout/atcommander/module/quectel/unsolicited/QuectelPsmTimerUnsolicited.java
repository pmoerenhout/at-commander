package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class QuectelPsmTimerUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +QPSMTIMER: 1320,30
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+QPSMTIMER: (\\d*),(\\d*)$");

  private int tau;
  private int t3324;

  public QuectelPsmTimerUnsolicited() {
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
      tau = Integer.parseInt(m.group(1));
      t3324 = Integer.parseInt(m.group(2));
      return;
    }
    throw createParseException(response);
  }

  public int getTau() {
    return tau;
  }

  public int getT3324() {
    return t3324;
  }
}