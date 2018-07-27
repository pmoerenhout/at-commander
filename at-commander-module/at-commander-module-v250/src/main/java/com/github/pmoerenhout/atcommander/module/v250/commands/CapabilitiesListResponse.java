package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class CapabilitiesListResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^(\\+.*)$");

  private List<String> capabilities;

  public CapabilitiesListResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    informationalText.forEach(line -> {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        capabilities.add(m.group(1));
        return;
      }
      throw createParseException(line);
    });
  }

  public List<String> getCapabilities() {
    return capabilities;
  }
}