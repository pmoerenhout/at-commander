package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module.v250.enums.PinStatus;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class PinResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CPIN: (.*)$");

  private PinStatus status;

  public PinResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        status = PinStatus.fromString(m.group(1));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public PinStatus getStatus() {
    return status;
  }
}
