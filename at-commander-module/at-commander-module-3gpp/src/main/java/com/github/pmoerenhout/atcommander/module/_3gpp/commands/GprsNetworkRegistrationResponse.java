package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class GprsNetworkRegistrationResponse extends BaseResponse implements Response {

  private static final String CGREG = "+CGREG: ";

  // "+CGREG: 2,5
  // "+CGREG: 2,1,\"0CBC\",\"D1A7\"";
  // "+CGREG: 2,1,\"0CBC\",\"D1A7\",0,\"01\"";
  private static final Pattern PATTERN = Pattern.compile("^\\+CGREG: (\\d),(\\d)(,\"([0-9A-F]*)\",\"([0-9A-F]*)\"(,\"(\\d)\",\"([0-9]*)\")?)?");
  protected RegistrationState registrationState;
  private Integer presentationMode;
  private String lac;
  private String cid;
  private AccessTechnology accessTechnology;
  private String rac;

  public GprsNetworkRegistrationResponse() {
  }

  public GprsNetworkRegistrationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final String[] tokens = Util.tokenize(StringUtils.stripStart(line, CGREG));
        presentationMode = Integer.valueOf(tokens[0]);
        registrationState = RegistrationState.fromString(tokens[1]);
        if (tokens.length >= 4) {
          lac = tokens[2];
          cid = tokens[3];
          if (tokens.length >= 6) {
            accessTechnology = AccessTechnology.fromInt(Integer.parseInt(tokens[4]));
            rac = tokens[5];
          }
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public Integer getPresentationMode() {
    return presentationMode;
  }

  public RegistrationState getRegistrationState() {
    return registrationState;
  }

  public String getLac() {
    return lac;
  }

  public String getCid() {
    return cid;
  }

  public AccessTechnology getAccessTechnology() {
    return accessTechnology;
  }

  public String getRac() {
    return rac;
  }
}
