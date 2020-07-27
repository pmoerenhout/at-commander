package com.github.pmoerenhout.atcommander.module.quectel.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.common.Util;

@Unsolicited
public class QuectelIndicationUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +QIND: SMS DONE
  // +QIND: PB DONE
  // +QIND: "SIM REFRESH"
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+QIND: (.*)$");

  private String indication;

  public QuectelIndicationUnsolicited() {
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
      indication = Util.removeQuotes(m.group(1));
      return;
    }
    throw createParseException(response);
  }

  public String getIndication() {
    return indication;
  }
}