package org.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.common.Util;
import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class RestrictedSimAccessResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CRSM: (\\d*),(\\d*)(,(.*)|)$");

  private static final byte[] NO_DATA = new byte[]{};

  private int sw1;
  private int sw2;
  private byte[] data;

  public RestrictedSimAccessResponse(final AtResponse s) {
    this.parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        sw1 = Integer.parseInt(m.group(1));
        sw2 = Integer.parseInt(m.group(2));
        final String dataString = m.group(4);
        data = dataString != null ? Util.hexToByteArray(dataString) : NO_DATA;
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getSw1() {
    return sw1;
  }

  public int getSw2() {
    return sw2;
  }

  public byte[] getData() {
    return data;
  }
}
