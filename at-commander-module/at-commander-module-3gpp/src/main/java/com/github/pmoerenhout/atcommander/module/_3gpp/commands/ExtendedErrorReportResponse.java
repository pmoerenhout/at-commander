package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ExtendedErrorReportResponse extends BaseResponse implements Response {

  //+CEER: text, could be spanning multiple lines

  private static final Pattern pattern = Pattern.compile("^\\+CEER: (.*)$");

  private List<String> report;

  public ExtendedErrorReportResponse() {
  }

  public ExtendedErrorReportResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    report = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (String line : informationalText){
      final Matcher m = pattern.matcher(line);
      if (m.find()) {
        report.add(m.group(1));
      } else {
        report.add(line);
      }
    }
  }

  public List<String> getReport() {
    return report;
  }
}
