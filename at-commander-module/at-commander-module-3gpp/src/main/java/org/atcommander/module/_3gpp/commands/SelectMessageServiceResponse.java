package org.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class SelectMessageServiceResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN3 = Pattern.compile("^\\+CSMS: (\\d),(\\d),(\\d)$");
  private static final Pattern PATTERN4 = Pattern.compile("^\\+CSMS: (\\d),(\\d),(\\d),(\\d)$");

  private int service;
  private int mt;
  private int mo;
  private int bm;

  public SelectMessageServiceResponse(final AtResponse s) {
    this.parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m3 = PATTERN3.matcher(line);
      if (m3.find()) {
        mt = Integer.parseInt(m3.group(1));
        mo = Integer.parseInt(m3.group(2));
        bm = Integer.parseInt(m3.group(3));
        return;
      }
      final Matcher m4 = PATTERN4.matcher(line);
      if (m4.find()) {
        service = Integer.parseInt(m4.group(1));
        mt = Integer.parseInt(m4.group(2));
        mo = Integer.parseInt(m4.group(3));
        bm = Integer.parseInt(m4.group(4));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getService() {
    return service;
  }

  public int getMt() {
    return mt;
  }

  public int getMo() {
    return mo;
  }

  public int getBm() {
    return bm;
  }
}
