package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class OperatorSelectionResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+COPS: ([0-4])(,(\\d),\"(.*)\"(,(\\d))?)?$");

  private int mode;
  private Integer format;
  private String oper;
  private Integer act;

  public OperatorSelectionResponse() {
  }

  public OperatorSelectionResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        mode = Integer.parseInt(m.group(1));
        if (m.group(2) != null) {
          format = Integer.valueOf(m.group(3));
          oper = m.group(4);
          if (m.group(5) != null) {
            act = Integer.valueOf(m.group(6));
          }
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getMode() {
    return mode;
  }

  public Integer getFormat() {
    return format;
  }

  public String getOper() {
    return oper;
  }

  public Integer getAct() {
    return act;
  }
}
