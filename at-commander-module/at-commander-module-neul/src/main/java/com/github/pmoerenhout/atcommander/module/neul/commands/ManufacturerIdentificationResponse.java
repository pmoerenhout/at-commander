package com.github.pmoerenhout.atcommander.module.neul.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ManufacturerIdentificationResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^(.*)$");

  private String manufacturer;

  public ManufacturerIdentificationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        manufacturer = m.group(1);
        return;
      }
      throw createParseException(line);
    }
  }

  public String getManufacturer() {
    return manufacturer;
  }

}
