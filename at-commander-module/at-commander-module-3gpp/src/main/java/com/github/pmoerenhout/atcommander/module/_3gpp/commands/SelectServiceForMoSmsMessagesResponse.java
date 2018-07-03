package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;

public class SelectServiceForMoSmsMessagesResponse extends BaseResponse implements Response {

  private final static Pattern PATTERN = Pattern.compile("^\\+CGSMS: (\\d)$");
  private static final Pattern PATTERNLIST = Pattern.compile("^\\+CGSMS: \\((.*)\\)$");

  private List<Integer> services;

  public SelectServiceForMoSmsMessagesResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    services = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        services = Collections.singletonList(Integer.valueOf(m.group(1)));
        return;
      } else {
        final Matcher matcherList = PATTERNLIST.matcher(line);
        if (matcherList.find()) {
          services = Util.toIntegerValues(matcherList.group(1));
          return;
        }
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getService() {
    if (services.size() != 1) {
      throw new IllegalStateException("Expecting only one service");
    }
    return services.get(0);
  }

  public List<Integer> getServices() {
    return services;
  }
}
