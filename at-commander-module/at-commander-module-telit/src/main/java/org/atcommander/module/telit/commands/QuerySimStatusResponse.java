package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.module._3gpp.SimStatus;

public class QuerySimStatusResponse extends BaseResponse implements Response, UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#QSS: (\\d)$");
  private static final Pattern PATTERN = Pattern.compile("^#QSS: (\\d),(\\d)$");

  private SimStatus status;
  private Integer mode;

  public QuerySimStatusResponse(final String s) {
    parse(s);
  }

  public QuerySimStatusResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final String line) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(line);
    if (m.find()) {
      status = SimStatus.fromInt(Integer.parseInt(m.group(1)));
      return;
    }
    throw createParseException(line);
  }

  public void parse(final AtResponse response) {
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
