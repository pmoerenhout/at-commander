package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.basic.commands.BaseResponse;

public class MessageTerminatingIndicationResponse extends BaseResponse implements Response {

  // +CMTI: "SM",3
  private static final Pattern PATTERN = Pattern.compile("^\\+CMTI: \"(.*)\",(\\d*)$");

  private String storage;
  private int index;

  public MessageTerminatingIndicationResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        storage = m.group(1);
        index = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getStorage() {
    return storage;
  }

  public int getIndex() {
    return index;
  }
}
