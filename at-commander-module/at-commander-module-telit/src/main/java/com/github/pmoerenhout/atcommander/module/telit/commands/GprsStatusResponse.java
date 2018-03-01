package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class GprsStatusResponse extends BaseResponse implements Response {

  final static Pattern pattern = Pattern.compile("^#GPRS: (\\d)$");

  private Boolean active;

  public GprsStatusResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = pattern.matcher(line);
      if (m.find()) {
        active = BooleanUtils.toBoolean(Integer.parseInt(m.group(1)));
        return;
      } else {
        throw createParseException(line);
      }
    }
    throw createParseException(response);
  }

  public Boolean getActive() {
    return active;
  }
}
