package org.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.basic.commands.BaseResponse;

public class SendMessageFromStorageResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CMSS: (\\d*)$");
  private int reference;

  public SendMessageFromStorageResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        reference = Integer.parseInt(m.group(1));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getReference() {
    return reference;
  }
}
