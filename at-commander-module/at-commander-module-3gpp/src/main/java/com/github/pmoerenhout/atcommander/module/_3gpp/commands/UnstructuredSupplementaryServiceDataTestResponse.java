package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;

public class UnstructuredSupplementaryServiceDataTestResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CUSD: (.*)$");

  private List<Integer> types = new ArrayList<>();

  public UnstructuredSupplementaryServiceDataTestResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        types = Util.toIntegerValues(Util.removeParenthesis(m.group(1)));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public List<Integer> getTypes() {
    return types;
  }

}
