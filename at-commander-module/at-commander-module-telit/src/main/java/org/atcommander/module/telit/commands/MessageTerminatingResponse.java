package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.basic.commands.BaseResponse;

public class MessageTerminatingResponse extends BaseResponse implements Response {

  //+CMT: "",36
  private static final Pattern PATTERN = Pattern.compile("^\\+CMT: \"(.*)\",(\\d*)$");

  private String alpha;
  private int length;

  public MessageTerminatingResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        alpha = m.group(1);
        length = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getAlpha() {
    return alpha;
  }

  public void setAlpha(final String alpha) {
    this.alpha = alpha;
  }

  public int getLength() {
    return length;
  }

  public void setLength(final int length) {
    this.length = length;
  }
}
