package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.ArrayList;
import java.util.List;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class AnyResponse extends BaseResponse implements Response {

  private List<String> lines = new ArrayList<>();

  public AnyResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    for (final String line : response.getInformationalText()) {
      lines.add(line);
    }
  }

  public List<String> getLines() {
    return lines;
  }
}



