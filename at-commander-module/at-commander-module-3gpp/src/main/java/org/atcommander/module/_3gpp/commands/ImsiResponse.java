package org.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class ImsiResponse extends BaseResponse implements Response {

  private static final  Pattern PATTERN = Pattern.compile("^(\\d*)$");

  private String imsi;

  public ImsiResponse(final AtResponse s) {
    this.parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        imsi = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getImsi() {
    return imsi;
  }

}
