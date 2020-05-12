package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class UnstructuredSupplementaryServiceDataUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +CUSD: <m>[,<str>,<dcs>]
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CUSD: (\\d)(,\"(.*)\"(,(\\d*))?)?$");

  private Integer response;
  private String ussdString;
  private Integer dcs;

  public UnstructuredSupplementaryServiceDataUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);

      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        response = Integer.valueOf(m.group(1));
        if (m.group(2) != null) {
          ussdString = m.group(3);
          if (m.group(4) != null) {
            dcs = Integer.valueOf(m.group(5));
          }
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

  public Integer getResponse() {
    return response;
  }

  public String getUssdString() {
    return ussdString;
  }

  public Integer getDcs() {
    return dcs;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UnstructuredSupplementaryServiceDataUnsolicited)) {
      return false;
    }
    final UnstructuredSupplementaryServiceDataUnsolicited that = (UnstructuredSupplementaryServiceDataUnsolicited) o;
    return Objects.equals(response, that.response) &&
        Objects.equals(ussdString, that.ussdString) &&
        Objects.equals(dcs, that.dcs);
  }

  @Override
  public int hashCode() {
    return Objects.hash(response, ussdString, dcs);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("UnstructuredSupplementaryServiceDataUnsolicited{");
    sb.append("response=").append(response);
    sb.append(", ussdString='").append(ussdString).append('\'');
    sb.append(", dcs=").append(dcs);
    sb.append('}');
    return sb.toString();
  }
}
