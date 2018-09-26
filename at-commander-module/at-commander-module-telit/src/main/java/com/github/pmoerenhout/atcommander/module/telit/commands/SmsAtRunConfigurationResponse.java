package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class SmsAtRunConfigurationResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN = Pattern.compile("^#SMSATRUNCFG: (\\d*),(\\d*),(\\d*)$");

  private int instance;
  private int urcMode;
  private int timeout;

  public SmsAtRunConfigurationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        instance = Integer.parseInt(m.group(1));
        urcMode = Integer.parseInt(m.group(2));
        timeout = Integer.parseInt(m.group(3));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getInstance() {
    return instance;
  }

  public int getUrcMode() {
    return urcMode;
  }

  public int getTimeout() {
    return timeout;
  }
}
