package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class SmsAtRunUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public final static Pattern UNSOLICITED_PATTERN = Pattern.compile("^#SMSATRUN: (.*)$");

  private String command;

  public SmsAtRunUnsolicited() {
  }

  @Override
  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        command = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getCommand() {
    return command;
  }
}
