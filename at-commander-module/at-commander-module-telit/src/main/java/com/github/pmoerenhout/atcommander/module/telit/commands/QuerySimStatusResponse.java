package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module._3gpp.SimStatus;

public class QuerySimStatusResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^#QSS: (\\d),(\\d)$");

  private SimStatus status;
  private Integer mode;

  public QuerySimStatusResponse() {
  }

  public QuerySimStatusResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m2 = PATTERN.matcher(line);
      if (m2.find()) {
        mode = Integer.parseInt(m2.group(1));
        status = SimStatus.fromInt(Integer.parseInt(m2.group(2)));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public SimStatus getStatus() {
    return status;
  }

  public Integer getMode() {
    return mode;
  }
}
