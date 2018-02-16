package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class GprsResponse extends BaseResponse implements Response {

  // intermediate response before OK
  private static final Pattern PATTERN = Pattern.compile("^\\+IP: (.*)$");

  private String ipAddress;

  public GprsResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 0) {
      // no response in case of deactivation
      return;
    }
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        this.ipAddress = m.group(1);
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