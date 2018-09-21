package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class GprsNetworkRegistrationUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // when <n>=0, 1, 2 or 3 and command successful:
  // +CGREG: <n>,<stat>[,[<lac>],[<ci>],[<AcT>],[ <rac>][,<cause_type>,<reject_cause>]]
  // when <n>=4 or 5 and command successful:
  // +CGREG: <n>,<stat>[,[<lac>],[<ci>],[<AcT>],[ <rac>][,[<cause_type>],[<reject_cause>][,[<A ctive-Time>],[<Periodic-RAU>],[<GPRS-READY- timer>]]]]

  // +CGREG: 1,"00DE","C1AD",0,"01"
  // +CGREG: 1,"00DE","FD4F",0,"01"

  public static final Pattern UNSOLICITED_PATTERN = Pattern
      .compile("^\\+CGREG: (\\d)(,\"([0-9A-F]*)\",\"([0-9A-F]*)\"(,(\\d)(,(\\d|),(\\d*|)(,(.*),(.*),(.*))?)?)?)?$");
  public static final Pattern UNSOLICITED_PATTERN1 = Pattern.compile("^\\+CGREG: (\\d)$");
  public static final Pattern UNSOLICITED_PATTERN2 = Pattern.compile("^\\+CGREG: (\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\"$");
  public static final Pattern UNSOLICITED_PATTERN3 = Pattern.compile("^\\+CGREG: (\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\",(\\d)$");
  //public static final Pattern UNSOLICITED_PATTERN4 = Pattern.compile("^\\+CGREG: (\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\",(\\d),\"(.*)\"$");

//  public static final Pattern UNSOLICITED_PATTERN4 = Pattern.compile("^\\+CGREG: (\\d),(\\d)$");
//  public static final Pattern UNSOLICITED_PATTERN5 = Pattern.compile("^\\+CGREG: (\\d),(\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\"$");
//  public static final Pattern UNSOLICITED_PATTERN6 = Pattern.compile("^\\+CGREG: (\\d),(\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\",(\\d)$");
  // private static final Pattern PATTERN6 = Pattern.compile("^\\+CGREG: (\\d),(\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\",(\\d),\"(.*)\"$");

  // protected Integer mode;
  protected RegistrationState registrationState;
  protected Integer lac;
  protected Integer cellId;
  protected AccessTechnology accessTechnology;
  private Integer causeType;
  private Integer rejectCause;
  private String activeTime;
  private String periodicRau;
  private String gprsReadyTimer;

  public GprsNetworkRegistrationUnsolicited() {
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        registrationState = RegistrationState.fromString(m.group(1));
        if (m.group(2) != null) {
          lac = Integer.valueOf(Integer.parseUnsignedInt(m.group(3), 16));
          cellId = Integer.valueOf(Integer.parseUnsignedInt(m.group(4), 16));
          if (m.group(5) != null) {
            accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m.group(6)));
            if (m.group(7) != null) {
              causeType = getInteger(m.group(8));
              rejectCause = getInteger(m.group(9));
              if (m.group(10) != null) {
                activeTime = m.group(11);
                periodicRau = m.group(12);
                gprsReadyTimer = m.group(13);
              }
            }
          }
        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(lines);
  }

//  protected void parse(final String line) {
//    final Matcher m1 = UNSOLICITED_PATTERN1.matcher(line);
//    if (m1.find()) {
//      registrationState = RegistrationState.fromString(m1.group(1));
//      return;
//    }
//    final Matcher m2 = UNSOLICITED_PATTERN2.matcher(line);
//    if (m2.find()) {
//      registrationState = RegistrationState.fromString(m2.group(1));
//      lac = Integer.parseUnsignedInt(m2.group(2), 16);
//      cellId = Integer.parseUnsignedInt(m2.group(3), 16);
//      return;
//    }
//    final Matcher m3 = UNSOLICITED_PATTERN3.matcher(line);
//    if (m3.find()) {
//      registrationState = RegistrationState.fromString(m3.group(1));
//      lac = Integer.parseUnsignedInt(m3.group(3), 16);
//      cellId = Integer.parseUnsignedInt(m3.group(3), 16);
//      accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m3.group(4)));
//      return;
//    }
//    final Matcher m4 = UNSOLICITED_PATTERN4.matcher(line);
//    if (m4.find()) {
//      mode = Integer.parseInt(m4.group(1));
//      registrationState = RegistrationState.fromString(m4.group(2));
//      return;
//    }
//    final Matcher m5 = UNSOLICITED_PATTERN5.matcher(line);
//    if (m5.find()) {
//      mode = Integer.parseInt(m5.group(1));
//      registrationState = RegistrationState.fromString(m5.group(2));
//      lac = Integer.parseUnsignedInt(m5.group(3), 16);
//      cellId = Integer.parseUnsignedInt(m5.group(4), 16);
//      return;
//    }
//    final Matcher m6 = UNSOLICITED_PATTERN6.matcher(line);
//    if (m6.find()) {
//      mode = Integer.parseInt(m6.group(1));
//      registrationState = RegistrationState.fromString(m6.group(2));
//      lac = Integer.parseUnsignedInt(m6.group(3), 16);
//      cellId = Integer.parseUnsignedInt(m6.group(4), 16);
//      accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m6.group(5)));
//      return;
//    }
//    throw createParseException(line);
//  }

//  public void parseSolicited(final AtResponse response) {
//    final List<String> informationalText = response.getInformationalText();
//    if (informationalText.size() == 1) {
//      final String line = informationalText.get(0);
//      final Matcher m2 = PATTERN2.matcher(line);
//      if (m2.find()) {
//        mode = Integer.parseInt(m2.group(1));
//        registrationState = RegistrationState.fromString(m2.group(2));
//        return;
//      }
//      final Matcher m4 = PATTERN4.matcher(line);
//      if (m4.find()) {
//        mode = Integer.parseInt(m4.group(1));
//        registrationState = RegistrationState.fromString(m4.group(2));
//        lac = Integer.parseUnsignedInt(m4.group(3), 16);
//        ci = Integer.parseUnsignedInt(m4.group(4), 16);
//        return;
//      }
//      final Matcher m5 = PATTERN5.matcher(line);
//      if (m5.find()) {
//        mode = Integer.parseInt(m5.group(1));
//        registrationState = RegistrationState.fromString(m5.group(2));
//        lac = Integer.parseUnsignedInt(m5.group(3), 16);
//        ci = Integer.parseUnsignedInt(m5.group(4), 16);
//        accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m5.group(5)));
//        return;
//      }
//      throw createParseException(line);
//    }
//    throw createParseException(response);
//  }

//  public Integer getMode() {
//    return mode;
//  }

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

  public Integer getCauseType() {
    return causeType;
  }

  public Integer getRejectCause() {
    return rejectCause;
  }

  public String getActiveTime() {
    return activeTime;
  }

  public String getPeriodicRau() {
    return periodicRau;
  }

  public String getGprsReadyTimer() {
    return gprsReadyTimer;
  }
}
