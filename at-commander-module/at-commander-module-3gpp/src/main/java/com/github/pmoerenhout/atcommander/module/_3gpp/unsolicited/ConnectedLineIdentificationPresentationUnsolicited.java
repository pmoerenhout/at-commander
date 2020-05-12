package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.common.Splitter;

@Unsolicited
public class ConnectedLineIdentificationPresentationUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +COLP: <number>,<type>[,<subaddr>,<satype> [,<alpha>]]

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+COLP: (.*)");

  private String number;
  private int type;
  private String subAddress;
  private Integer subAddressType;
  private String alpha;

  public ConnectedLineIdentificationPresentationUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  private void parse(final String response) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(response);
    if (m.find()) {
      final Splitter splitter = new Splitter(m.group(1));
      number = splitter.getString(0);
      type = splitter.getInteger(1);
      subAddress = splitter.getString(2);
      subAddressType = splitter.getInteger(3);
      alpha = splitter.getString(4);
      return;
    }
    throw createParseException(response);
  }

  public String getNumber() {
    return number;
  }

  public int getType() {
    return type;
  }

  public String getSubAddress() {
    return subAddress;
  }

  public Integer getSubAddressType() {
    return subAddressType;
  }

  public String getAlpha() {
    return alpha;
  }
}