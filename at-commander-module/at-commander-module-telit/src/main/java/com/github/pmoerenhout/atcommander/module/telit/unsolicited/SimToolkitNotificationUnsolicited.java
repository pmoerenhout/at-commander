package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.common.Util;

public class SimToolkitNotificationUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // https://www.telit.com/wp-content/uploads/2017/09/Telit_SIM_USIM_Toolkit_Application_Note_r5.pdf

  // 1: #STN: <cmdType>,<refresh type>
  // 17,19,20,32: #STN: <cmdType>[,<text>]

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#STN: (.*)$");

  private Integer commandType;
  private Integer refreshType;

  public SimToolkitNotificationUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        final String[] tokens = Util.tokenize(m.group(1));
        commandType = Integer.valueOf(tokens[0]);
        if (tokens.length > 1) {
          refreshType = Integer.valueOf(tokens[1]);
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public Integer getCommandType() {
    return commandType;
  }

  public Integer getRefreshType() {
    return refreshType;
  }
}
