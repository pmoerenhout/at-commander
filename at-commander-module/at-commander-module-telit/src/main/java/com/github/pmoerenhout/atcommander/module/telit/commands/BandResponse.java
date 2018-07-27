package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.module.v250.enums.UmtsBand;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.module.telit.enums.GsmBand;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class BandResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN1 = Pattern.compile("^#BND: ([0-3])$");
  private static final Pattern PATTERN2 = Pattern.compile("^#BND: ([0-3]),([0-7])$");

  private GsmBand band;
  private UmtsBand umtsBand;

  public BandResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m1 = PATTERN1.matcher(line);
      if (m1.find()) {
        band = GsmBand.fromInt(Integer.parseInt(m1.group(1)));
        return;
      }

      final Matcher m2 = PATTERN2.matcher(line);
      if (m2.find()) {
        band = GsmBand.fromInt(Integer.parseInt(m2.group(1)));
        umtsBand = UmtsBand.fromInt(Integer.parseInt(m2.group(2)));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public GsmBand getBand() {
    return band;
  }

  public UmtsBand getUmtsBand() {
    return umtsBand;
  }
}
