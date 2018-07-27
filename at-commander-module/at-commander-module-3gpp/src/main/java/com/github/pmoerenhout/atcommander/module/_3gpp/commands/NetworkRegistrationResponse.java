package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class NetworkRegistrationResponse extends BaseResponse implements Response, UnsolicitedResponse {

  // +CREG: <stat> when <n> = 1
  public static final Pattern UNSOLICITED_PATTERN1 = Pattern.compile("^\\+CREG: (\\d)$");
  // +CREG: <stat>[,<lac>,<ci>] when <n> = 2
  public static final Pattern UNSOLICITED_PATTERN2 = Pattern.compile("^\\+CREG: (\\d),\"(.*)\",\"(.*)\"$");
  // +CREG: <stat>[,<lac>,<ci>,<act>] when <n> = 3
  public static final Pattern UNSOLICITED_PATTERN3 = Pattern.compile("^\\+CREG: (\\d),\"(.*)\",\"(.*)\",(\\d)$");

  private static final Pattern PATTERN = Pattern.compile("^\\+CREG: (.*)$");
//  private static final Pattern PATTERN2 = Pattern.compile("^\\+CREG: (\\d),(\\d)$");
//  private static final Pattern PATTERN4 = Pattern.compile("^\\+CREG: (\\d),(\\d),\"(.*)\",\"(.*)\"$");
  // private static final Pattern PATTERN5 = Pattern.compile("^\\+CREG: (\\d),(\\d),\"(.*)\",\"(.*)\",(\\d)$");

  protected Integer mode;
  protected RegistrationState registrationState;
  protected Integer lac;
  protected Integer cellId;
  protected AccessTechnology accessTechnology;

  public NetworkRegistrationResponse() {
  }

  public NetworkRegistrationResponse(final AtResponse response) {
    parseSolicited(response);
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  protected void parse(final String line) {
    final Matcher m1 = UNSOLICITED_PATTERN1.matcher(line);
    if (m1.find()) {
      registrationState = RegistrationState.fromString(m1.group(1));
      return;
    }
    final Matcher m2 = UNSOLICITED_PATTERN2.matcher(line);
    if (m2.find()) {
      registrationState = RegistrationState.fromString(m2.group(1));
      lac = Integer.parseUnsignedInt(m2.group(2), 16);
      cellId = Integer.parseUnsignedInt(m2.group(3), 16);
      // accessTechnology = AccessTechnology.fromInt(Integer.parseUnsignedInt(tokens[3], 16));
      return;
    }
    final Matcher m3 = UNSOLICITED_PATTERN3.matcher(line);
    if (m3.find()) {
      registrationState = RegistrationState.fromString(m3.group(1));
      lac = Integer.parseUnsignedInt(m3.group(2), 16);
      cellId = Integer.parseUnsignedInt(m3.group(3), 16);
      accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m3.group(4)));
      return;
    }
    throw createParseException(line);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final String[] tokens = Util.tokenize(m.group(1));
        mode = Integer.parseInt(tokens[0]);
        registrationState = RegistrationState.fromString(tokens[1]);
        if (tokens.length > 2) {
          lac = Integer.parseUnsignedInt(tokens[2], 16);
          cellId = Integer.parseUnsignedInt(tokens[3], 16);
          if (tokens.length > 4) {
            accessTechnology = AccessTechnology.fromInt(Integer.parseUnsignedInt(tokens[4], 16));
          }
        }
        return;
      }

//      final Matcher m5 = PATTERN5.matcher(line);
//      if (m5.find()) {
//        mode = Integer.parseInt(m5.group(1));
//        registrationState = RegistrationState.fromString(m5.group(2));
//        lac = Integer.parseUnsignedInt(m5.group(3), 16);
//        cellId = Integer.parseUnsignedInt(m5.group(4), 16);
//        accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m5.group(5)));
//        return;
//      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public Integer getMode() {
    return mode;
  }

  public RegistrationState getRegistrationState() {
    return registrationState;
  }

  public Integer getLac() {
    return lac;
  }

  public Integer getCellId() {
    return cellId;
  }

  public AccessTechnology getAccessTechnology() {
    return accessTechnology;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final NetworkRegistrationResponse that = (NetworkRegistrationResponse) o;
    return Objects.equals(mode, that.mode) &&
        registrationState == that.registrationState &&
        Objects.equals(lac, that.lac) &&
        Objects.equals(cellId, that.cellId);
  }

  @Override
  public int hashCode() {

    return Objects.hash(mode, registrationState, lac, cellId);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("NetworkRegistrationResponse{");
    sb.append("mode=").append(mode);
    sb.append(", registrationState=").append(registrationState);
    sb.append(", lac=").append(lac);
    sb.append(", cellId=").append(cellId);
    sb.append('}');
    return sb.toString();
  }
}
