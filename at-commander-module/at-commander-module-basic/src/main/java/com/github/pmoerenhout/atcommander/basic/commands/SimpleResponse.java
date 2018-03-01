package com.github.pmoerenhout.atcommander.basic.commands;

import java.util.List;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;

public class SimpleResponse extends BaseResponse implements Response {

  private String value;

  public SimpleResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      value = informationalText.get(0);
      return;
    }
    throw new ParseException("The response has incorrect number of lines: " + informationalText.size());
  }

  public String getValue() {
    return value;
  }

}
