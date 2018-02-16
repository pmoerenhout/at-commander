package org.atcommander.module.v250.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class CapabilitiesListResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^(\\+.*)$");

  private List<String> capabilities;

  public CapabilitiesListResponse(final AtResponse s) {
    this.parse(s);
  }

  public void parse(final AtResponse response) {
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