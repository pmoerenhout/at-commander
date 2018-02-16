package org.atcommander.module.neul.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class NeulConfigResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+NCONFIG:(.*),(.*)$");

  private List<Pair> items = new ArrayList<>();

  public NeulConfigResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    for (String line: informationalText) {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        items.add(new ImmutablePair(m.group(1), m.group(2)));
      }
    }
  }

  public List<Pair> getItems() {
    return items;
  }
}
