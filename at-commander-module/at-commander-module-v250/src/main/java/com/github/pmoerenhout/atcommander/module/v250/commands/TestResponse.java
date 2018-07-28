package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;

public class TestResponse extends BaseResponse implements Response {

  private static final Logger LOG = LoggerFactory.getLogger(TestResponse.class);

  private final List<String> values = new ArrayList<>();

  public TestResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String r = StringUtils.substringBetween(informationalText.get(0), "(", ")");
      final String[] splitted = StringUtils.split(r, ',');
      for (final  String t : splitted) {
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
      LOG.warn("Received not 1 line, but {}", informationalText.size());
    }
  }

  public List<String> getValues() {
    return values;
  }
}
