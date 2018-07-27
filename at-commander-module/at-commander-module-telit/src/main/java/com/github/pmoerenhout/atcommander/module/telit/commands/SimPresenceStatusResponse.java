package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module.telit.types.SimPresence;

public class SimPresenceStatusResponse extends BaseResponse implements Response, UnsolicitedResponse {

  public static final Pattern UNSOLICTED_PATTERN = Pattern.compile("^#SIMPR: ([0-1]),([0-1])$");

  private static final Pattern PATTERN = Pattern.compile("^#SIMPR: ([0-1]),([0-1]),([0-1])$");

  private List<SimPresence> simPresences = new ArrayList<>();

  public SimPresenceStatusResponse() {
  }

  public SimPresenceStatusResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  private void parse(final String response) {
    final Matcher m1 = UNSOLICTED_PATTERN.matcher(response);
    if (m1.find()) {
      final boolean remote = BooleanUtils.toBoolean(Integer.parseInt(m1.group(1)));
      final boolean inserted = BooleanUtils.toBoolean(Integer.parseInt(m1.group(2)));
      simPresences.add(new SimPresence(null, remote, inserted));
      return;
    }
    throw createParseException(response);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() > 0) {
      for (final String line : informationalText) {
        final Matcher m = PATTERN.matcher(line);
        if (m.find()) {
          final Integer mode = Integer.valueOf(m.group(1));
          final boolean remote = BooleanUtils.toBoolean(Integer.parseInt(m.group(2)));
          final boolean inserted = BooleanUtils.toBoolean(Integer.parseInt(m.group(3)));
          simPresences.add(new SimPresence(mode, remote, inserted));
        } else {
          throw createParseException(line);
        }
      }
    } else {
      throw createParseException(response);
    }
  }

  public List<SimPresence> getSimPresences() {
    return simPresences;
  }

}
