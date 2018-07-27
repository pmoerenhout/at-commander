package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class EasyGprsActivationResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN_IP4 = Pattern.compile("^#SGACT: (.*)$");
  private final static Pattern PATTERN_IP6 = Pattern.compile("^\\+IP: (.*)$");

  private String ipAddress;

  public EasyGprsActivationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 0) {
      // deactivation is without response
      return;
    } else if (informationalText.size() == 1) {
      // activation is with response
      final String line = informationalText.get(0);
      final Matcher m4 = PATTERN_IP4.matcher(line);
      if (m4.find()) {
        this.ipAddress = m4.group(1);
        return;
      }
      final Matcher m6 = PATTERN_IP6.matcher(line);
      if (m6.find()) {
        this.ipAddress = m6.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getIpAddress() {
    return ipAddress;
  }
}
