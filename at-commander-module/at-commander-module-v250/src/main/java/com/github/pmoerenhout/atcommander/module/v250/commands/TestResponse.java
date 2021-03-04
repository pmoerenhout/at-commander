package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestResponse extends BaseResponse implements Response {

  private final List<String> values = new ArrayList<>();

  public TestResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String r = StringUtils.substringBetween(informationalText.get(0), "(", ")");
      final String[] splitted = StringUtils.split(r, ',');
      for (final String t : splitted) {
        if (t.contains("-")) {
          final String min = StringUtils.substringBefore(t, "-");
          final String max = StringUtils.substringAfter(t, "-");
          values.add(min);
          values.add(max);
        } else {
          values.add(Util.removeQuotes(t));
        }
      }
    } else {
      log.warn("Received not 1 line, but {}", informationalText.size());
    }
  }

  public List<String> getValues() {
    return values;
  }
}
