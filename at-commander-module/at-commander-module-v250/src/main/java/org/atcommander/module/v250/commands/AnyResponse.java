package org.atcommander.module.v250.commands;

import java.util.ArrayList;
import java.util.List;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class AnyResponse extends BaseResponse implements Response {

  private List<String> lines = new ArrayList<>();

  public AnyResponse(final AtResponse s) {
    this.parse(s);
  }

  public void parse(final AtResponse response) {
    for (final String line : response.getInformationalText()) {
      lines.add(line);
    }
  }

  public List<String> getLines() {
    return lines;
  }
}



