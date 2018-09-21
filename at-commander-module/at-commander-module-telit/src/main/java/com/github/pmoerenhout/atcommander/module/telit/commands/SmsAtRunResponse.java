package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class SmsAtRunResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN = Pattern.compile("^#SMSATMODE: (\\d*),(\\d*)$");

  private int mode;
  private int status;

  public SmsAtRunResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        mode = Integer.parseInt(m.group(1));
        status = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getMode() {
    return mode;
  }

  public int getStatus() {
    return status;
  }
}
