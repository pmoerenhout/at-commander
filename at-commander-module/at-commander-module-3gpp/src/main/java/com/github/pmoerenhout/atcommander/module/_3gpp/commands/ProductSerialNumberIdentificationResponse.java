package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ProductSerialNumberIdentificationResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^(\\d*)$");

  private String productSerialNumber;

  public ProductSerialNumberIdentificationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        productSerialNumber = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(informationalText);
  }

  public String getProductSerialNumber() {
    return productSerialNumber;
  }
}
