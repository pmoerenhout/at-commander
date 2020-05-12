package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

@Unsolicited
public class MessageTerminatingUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +CMT: "",36
  // +CMT: "",22<0d><0a>07911346101919F9040B911316240486F900008170405110338003D0741B
  // Quectel: +CMT: ,51<0d>07911346101919F9040B911316240486F900008170405110338003D0741B
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CMT: (\"(.*)\")?,(.*)$");
  private String alpha;
  private int length;
  private String pdu;

  public MessageTerminatingUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() != 2) {
      createParseException(lines);
    }
    final Matcher m = UNSOLICITED_PATTERN.matcher(lines.get(0));
    if (m.find()) {
      alpha = m.group(2);
      length = Integer.parseInt(m.group(3));
      pdu = lines.get(1);
      return;
    }
    throw createParseException(lines);
  }

  public String getAlpha() {
    return alpha;
  }

  public void setAlpha(final String alpha) {
    this.alpha = alpha;
  }

  public int getLength() {
    return length;
  }

  public void setLength(final int length) {
    this.length = length;
  }

  public String getPdu() {
    return pdu;
  }

  public void setPdu(final String pdu) {
    this.pdu = pdu;
  }
}
