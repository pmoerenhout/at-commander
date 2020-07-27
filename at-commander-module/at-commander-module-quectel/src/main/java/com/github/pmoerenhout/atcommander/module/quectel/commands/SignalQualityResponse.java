package com.github.pmoerenhout.atcommander.module.quectel.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class SignalQualityResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+QCSQ: \"(.*)\"(,([-\\d]*)(,([-\\d]*)(,([-\\d]*)(,([-\\d]*))?)?)?)?$");

  private String sysmode;
  private Integer rssi;
  private Integer rsrp;
  private Integer sinr;
  private Integer rsrq;

  public SignalQualityResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        sysmode = m.group(1);
        switch (sysmode) {
          case "NOSERVICE":
            break;
          case "GSM":
            rssi = Integer.parseInt(m.group(3));
            break;
          case "CAT-M1":
          case "CAT-NB1":
            rssi = Integer.parseInt(m.group(3));
            rsrp = Integer.parseInt(m.group(5));
            sinr = Integer.parseInt(m.group(7));
            rsrq = Integer.parseInt(m.group(9));
            break;
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getSysmode() {
    return sysmode;
  }

  public Integer getRssi() {
    return rssi;
  }

  public Integer getRsrp() {
    return rsrp;
  }

  public Integer getSinr() {
    return sinr;
  }

  public Integer getRsrq() {
    return rsrq;
  }
}
