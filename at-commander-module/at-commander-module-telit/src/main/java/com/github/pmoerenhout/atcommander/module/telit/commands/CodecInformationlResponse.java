package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;

public class CodecInformationlResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#CODECINFO: (.*)$");

  private String usedCodec;

  public CodecInformationlResponse(final String s) {
    parse(s);
  }

  public void parse(final String response) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(response);
    if (m.find()) {
      usedCodec = m.group(1);
      return;
    }
    throw new ParseException("Could not parse response: " + response);
  }

  public String getUsedCodec() {
    return usedCodec;
  }
}
