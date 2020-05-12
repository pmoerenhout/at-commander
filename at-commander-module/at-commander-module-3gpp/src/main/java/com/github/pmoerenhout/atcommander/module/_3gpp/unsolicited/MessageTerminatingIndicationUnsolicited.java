package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class MessageTerminatingIndicationUnsolicited extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CMTI: \"(.*)\",(\\d*)$");

  private String storage;
  private int index;

  public MessageTerminatingIndicationUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        storage = m.group(1);
        index = Integer.parseUnsignedInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public String getStorage() {
    return storage;
  }

  public int getIndex() {
    return index;
  }
}
