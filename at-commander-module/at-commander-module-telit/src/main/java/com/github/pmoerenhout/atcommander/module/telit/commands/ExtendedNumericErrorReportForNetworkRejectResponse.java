package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ExtendedNumericErrorReportForNetworkRejectResponse extends BaseResponse implements Response {

  // #CEERNET: 43
  private static final Pattern pattern = Pattern.compile("^\\#CEERNET: (\\d*)$");

  private int code;

  public ExtendedNumericErrorReportForNetworkRejectResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = pattern.matcher(line);
      if (m.find()) {
        code = Integer.valueOf(m.group(1));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getCode() {
    return code;
  }
}
