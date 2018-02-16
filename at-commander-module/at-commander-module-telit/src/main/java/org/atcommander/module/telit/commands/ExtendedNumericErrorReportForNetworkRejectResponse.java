package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.basic.commands.BaseResponse;

public class ExtendedNumericErrorReportForNetworkRejectResponse extends BaseResponse implements Response {

  // #CEERNET: 43
  private static final Pattern pattern = Pattern.compile("^\\#CEERNET: (\\d*)$");

  private int code;

  public ExtendedNumericErrorReportForNetworkRejectResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
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
