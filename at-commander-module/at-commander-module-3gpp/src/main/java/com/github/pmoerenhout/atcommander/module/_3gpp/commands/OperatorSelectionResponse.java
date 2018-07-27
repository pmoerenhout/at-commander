package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class OperatorSelectionResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN1 = Pattern.compile("^\\+COPS: ([0-4])$");
  private static final Pattern PATTERN2 = Pattern.compile("^\\+COPS: ([0-4]),(\\d),\"(.*)\"$");
  private static final Pattern PATTERN3 = Pattern.compile("^\\+COPS: ([0-4]),(\\d),\"(.*)\",(\\d)$");

  private int mode;
  private Integer format;
  private String oper;
  private Integer act;

  public OperatorSelectionResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m1 = PATTERN1.matcher(line);
      if (m1.find()) {
        //mode = PacketServiceNetworkType.fromInt(Integer.parseInt(m.group(1)));
        mode = Integer.parseInt(m1.group(1));
        return;
      }
      final Matcher m2 = PATTERN2.matcher(line);
      if (m2.find()) {
        mode = Integer.parseInt(m2.group(1));
        format = Integer.parseInt(m2.group(2));
        oper = m2.group(3);
        return;
      }
      final Matcher m3 = PATTERN3.matcher(line);
      if (m3.find()) {
        mode = Integer.parseInt(m3.group(1));
        format = Integer.parseInt(m3.group(2));
        oper = m3.group(3);
        act = Integer.parseInt(m3.group(4));
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
