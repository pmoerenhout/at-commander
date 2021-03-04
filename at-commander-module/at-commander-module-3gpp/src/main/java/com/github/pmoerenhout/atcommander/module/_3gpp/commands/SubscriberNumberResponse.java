package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;
import com.github.pmoerenhout.atcommander.common.Util;

public class SubscriberNumberResponse extends BaseResponse implements Response {

  // +CNUM: ,"+31682346962",145

  public static final int TYPE_UNKNOWN = 129;
  public static final int TYPE_INTERNATIONAL = 145;
  public static final int TYPE_NATIONAL = 161;

  private static final String CNUM = "+CNUM: ";

  private String alpha;
  private String number;
  private int type;

  public SubscriberNumberResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final String[] tokens = Util.tokenize(StringUtils.stripStart(line, CNUM));
      final int length = tokens.length;
      if (length == 0) {
        throw new ParseException("Could not parse response: " + line);
      }
      if (length == 3) {
        alpha = getToken(tokens, 0);
        number = getToken(tokens, 1);
        type = getTokenAsInteger(tokens, 2);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getAlpha() {
    return alpha;
  }

  public String getNumber() {
    return number;
  }

  public int getType() {
    return type;
  }
}
