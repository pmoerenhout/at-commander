package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class NewMessageIndicationsResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CNMI: (\\d),(\\d),(\\d),(\\d),(\\d)$");

  private int mode;
  private int mt;
  private int bm;
  private int ds;
  private int bfr;

  public NewMessageIndicationsResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        mode = Integer.parseInt(m.group(1));
        mt = Integer.parseInt(m.group(2));
        bm = Integer.parseInt(m.group(3));
        ds = Integer.parseInt(m.group(4));
        bfr = Integer.parseInt(m.group(5));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getMode() {
    return mode;
  }

  public int getMt() {
    return mt;
  }

  public int getBm() {
    return bm;
  }

  public int getDs() {
    return ds;
  }

  public int getBfr() {
    return bfr;
  }
}
