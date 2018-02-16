package org.atcommander.module.v250.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class ManufacturerIdentificationResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^(.*)$");

  private String manufacturer;

  public ManufacturerIdentificationResponse(final AtResponse s) {
    this.parse(s);
  }

  public void parse(final AtResponse response) {
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
