package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class PreferredMessageStorageResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CPMS: \"(.*)\",(\\d*),(\\d*),\"(.*)\",(\\d*),(\\d*),\"(.*)\",(\\d*),(\\d*)$");

  private String mem1;
  private int used1;
  private int total1;
  private String mem2;
  private int used2;
  private int total2;
  private String mem3;
  private int used3;
  private int total3;

  public PreferredMessageStorageResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        mem1 = m.group(1);
        used1 = Integer.parseInt(m.group(2));
        total1 = Integer.parseInt(m.group(3));
        mem2 = m.group(4);
        used2 = Integer.parseInt(m.group(5));
        total2 = Integer.parseInt(m.group(6));
        mem3 = m.group(7);
        used3 = Integer.parseInt(m.group(8));
        total3 = Integer.parseInt(m.group(9));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getMem1() {
    return mem1;
  }

  public int getUsed1() {
    return used1;
  }

  public int getTotal1() {
    return total1;
  }

  public String getMem2() {
    return mem2;
  }

  public int getUsed2() {
    return used2;
  }

  public int getTotal2() {
    return total2;
  }

  public String getMem3() {
    return mem3;
  }

  public int getUsed3() {
    return used3;
  }

  public int getTotal3() {
    return total3;
  }
}
