package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class MessageFormatResponse extends BaseResponse implements Response {

  //+CMGF: 0, +CMGF: 1
  private static final Pattern pattern = Pattern.compile("^\\+CMGF: ([0|1])$");

  private int format;

  public MessageFormatResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = pattern.matcher(line);
      if (m.find()) {
        format = Integer.parseInt(m.group(1));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getFormat() {
    return format;
  }
}
