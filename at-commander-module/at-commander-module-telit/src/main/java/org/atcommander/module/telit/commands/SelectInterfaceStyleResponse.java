package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class SelectInterfaceStyleResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN = Pattern.compile("^#SELINT: (\\d*)$");

  private int interfaceStyle;

  public SelectInterfaceStyleResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        interfaceStyle = Integer.parseInt(m.group(1));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getInterfaceStyle() {
    return interfaceStyle;
  }
}
