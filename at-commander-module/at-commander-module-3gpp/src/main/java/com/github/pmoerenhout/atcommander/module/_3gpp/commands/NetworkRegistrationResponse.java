package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;

public class NetworkRegistrationResponse extends BaseResponse implements Response, UnsolicitedResponse {

  // +CREG: <stat> when <n> = 1
  public static final Pattern UNSOLICITED_PATTERN1 = Pattern.compile("^\\+CREG: (\\d)$");
  // +CREG: <stat>[,<lac>,<ci>] when <n> = 2
  public static final Pattern UNSOLICITED_PATTERN2 = Pattern.compile("^\\+CREG: (\\d),\"(.*)\",\"(.*)\"$");
  // public static final Pattern UNSOLICITED_PATTERN3 = Pattern.compile("^\\+CREG: (\\d),\"(.*)\",\"(.*)\",(\\d)$");

  private static final Pattern PATTERN2 = Pattern.compile("^\\+CREG: (\\d),(\\d)$");
  private static final Pattern PATTERN4 = Pattern.compile("^\\+CREG: (\\d),(\\d),\"(.*)\",\"(.*)\"$");
  // private static final Pattern PATTERN5 = Pattern.compile("^\\+CREG: (\\d),(\\d),\"(.*)\",\"(.*)\",(\\d)$");

  protected Integer mode;
  protected RegistrationState registrationState;
  protected Integer lac;
  protected Integer cellId;

  protected NetworkRegistrationResponse() {
  }

  public NetworkRegistrationResponse(final String line) {
    parse(line);
  }

  public NetworkRegistrationResponse(final AtResponse response) {
    parse(response);
  }

  public void parse(final String line) {
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
      return;
    }
//    final Matcher m3 = UNSOLICITED_PATTERN3.matcher(line);
//    if (m3.find()) {
//      registrationState = RegistrationState.fromString(m3.group(1));
//      lac = Integer.parseUnsignedInt(m3.group(2), 16);
//      cellId = Integer.parseUnsignedInt(m3.group(3), 16);
//      accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m3.group(4)));
//      return;
//    }
    throw createParseException(line);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m2 = PATTERN2.matcher(line);
      if (m2.find()) {
        mode = Integer.parseInt(m2.group(1));
        registrationState = RegistrationState.fromString(m2.group(2));
        return;
      }
      final Matcher m4 = PATTERN4.matcher(line);
      if (m4.find()) {
        mode = Integer.parseInt(m4.group(1));
        registrationState = RegistrationState.fromString(m4.group(2));
        lac = Integer.parseUnsignedInt(m4.group(3), 16);
        cellId = Integer.parseUnsignedInt(m4.group(4), 16);
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

}
