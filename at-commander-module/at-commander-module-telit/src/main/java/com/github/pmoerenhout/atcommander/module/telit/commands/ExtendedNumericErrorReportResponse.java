package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ExtendedNumericErrorReportResponse extends BaseResponse implements Response {

  // #CEER: 43
  final static Pattern pattern = Pattern.compile("^\\#CEER: (\\d*)$");

  private int code;

  public ExtendedNumericErrorReportResponse(final AtResponse s) {
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
