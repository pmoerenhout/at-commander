package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class CellularResultCodesResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CRC: (\\d)$");
  private static final Pattern PATTERNLIST = Pattern.compile("^\\+CRC: \\((.*)\\)$");

  private List<String> modes;

  public CellularResultCodesResponse(final AtResponse s) {
    this.parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    modes = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        modes = Collections.singletonList(m.group(1));
        return;
      } else {
        final Matcher matcherList = PATTERNLIST.matcher(line);
        if (matcherList.find()) {
          for (final String mode : StringUtils.split(matcherList.group(1), COMMA)) {
            modes.add(mode);
          }
          return;
        }
      }
      throw createParseException(line);
    }
  }

  public String getMode() {
    if (modes.size() != 1) {
      throw new IllegalStateException("Expecting only one mode");
    }
    return modes.get(0);
  }

  public List<String> getModes() {
    return modes;
  }
}
