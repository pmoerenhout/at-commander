package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class QuectelUsimUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +QUSIM: <0|1>>
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+QUSIM: (\\d)$");

  private int usim;

  public QuectelUsimUnsolicited() {
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
      usim = Integer.valueOf(m.group(1));
      return;
    }
    throw createParseException(response);
  }

  public int getUsim() {
    return usim;
  }
}