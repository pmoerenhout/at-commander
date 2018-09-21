package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;

public class ServiceCentreAddressResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CSCA: \"(.*)\",([0-9]*)$");

  // <type> - the type of number
  // 129 - national numbering scheme
  // 145 - international numbering scheme (contains the character "+")

  private String number;
  private int type;

  public ServiceCentreAddressResponse(final AtResponse s) throws ParseException {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) throws ParseException {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        number = m.group(1);
        type = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getNumber() {
    return number;
  }

  public int getType() {
    return type;
  }
}
