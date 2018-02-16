package org.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.exceptions.ParseException;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class ServiceCentreAddressResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CSCA: \"([+0-9]*)\",([0-9]*)$");

  private String number;
  private int type;

  public ServiceCentreAddressResponse(final AtResponse s) throws ParseException {
    parse(s);
  }

  public void parse(final AtResponse response) throws ParseException {
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
