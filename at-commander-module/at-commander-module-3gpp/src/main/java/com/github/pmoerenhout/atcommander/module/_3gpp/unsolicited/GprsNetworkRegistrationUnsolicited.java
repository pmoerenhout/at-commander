package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import static com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsNetworkRegistrationResponse.CGREG;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

@Unsolicited
public class GprsNetworkRegistrationUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // 3GPP TS 27.007 version 10.3.0 Release 10
  // https://www.etsi.org/deliver/etsi_ts/127000_127099/127007/10.03.00_60/ts_127007v100300p.pdf
  // https://www.etsi.org/deliver/etsi_ts/127000_127099/127007/13.04.00_60/ts_127007v130400p.pdf

  // when <n>=0, 1, 2 or 3 and command successful:
  // +CGREG: <n>,<stat>[,[<lac>],[<ci>],[<AcT>],[ <rac>][,<cause_type>,<reject_cause>]]
  // when <n>=4 or 5 and command successful:
  // +CGREG: <n>,<stat>[,[<lac>],[<ci>],[<AcT>],[ <rac>][,[<cause_type>],[<reject_cause>][,[<Active-Time>],[<Periodic-RAU>],[<GPRS-READY- timer>]]]]

  // 2
  // +CGREG: <stat>[,<[lac>,]<[ci>],[<AcT>],[<rac>]]
  // 3
  // +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,<cause_type>,<reject_cause>]]
  // 4
  // +CGREG:  +CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,,[,[<Active-Time>],[<Periodic-RAU>],[<GPRS-READY-timer>]]]]
  // 5
  // CGREG: <stat>[,[<lac>],[<ci>],[<AcT>],[<rac>][,[<cause_type>],[<reject_cause>][,[<Active-Time>],[<Periodic-RAU>],[<GPRS-READY-timer>]]]]

  // +CGREG: 1,"00DE","C1AD",0,"01"
  // +CGREG: 1,"00DE","FD4F",0,"01"
  // +CGREG: 1,"00DE","0A5B",0,"01"

  // public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CGREG: (\\d)((,\"([0-9A-F]*)\",\"([0-9A-F]*)\")?(,.*))?$");
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CGREG: (\\d)(,\"([0-9A-F]*)\",\"([0-9A-F]*)\"(,.*)?)?$");

  protected RegistrationState registrationState;
  protected Integer lac;
  protected Integer cellId;
  protected AccessTechnology accessTechnology;
  protected Byte routingAreaCode;
  private Integer causeType;
  private Integer rejectCause;
  private String activeTime;
  private String periodicRau;
  private String gprsReadyTimer;

  public GprsNetworkRegistrationUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
  }

  public void parseUnsolicited(final List<String> lines) {
    if (lines.size() == 1) {
      final String line = lines.get(0);
      final Matcher m = UNSOLICITED_PATTERN.matcher(line);
      if (m.find()) {
        final String[] tokens = Util.tokenize(StringUtils.stripStart(line, CGREG));
        registrationState = RegistrationState.fromString(tokens[0]);
        if (tokens.length > 1) {
          lac = Integer.valueOf(Integer.parseUnsignedInt(tokens[1], 16));
          cellId = Integer.valueOf(Integer.parseUnsignedInt(tokens[2], 16));
          if (tokens.length > 3) {
            accessTechnology = AccessTechnology.fromInt(Integer.parseInt(tokens[3]));
            if (tokens.length > 4) {
              routingAreaCode = Byte.valueOf(tokens[4], 16);
              if (tokens.length > 5) {
                causeType = getInteger(tokens[5]);
                rejectCause = getInteger(tokens[6]);
                if (tokens.length > 7) {
                  activeTime = m.group(tokens[7]);
                  periodicRau = m.group(tokens[8]);
                  gprsReadyTimer = m.group(tokens[9]);
                }
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

  public Optional<Integer> getLac() {
    return Optional.ofNullable(lac);
  }

  public Optional<Integer> getCellId() {
    return Optional.ofNullable(cellId);
  }

  public Optional<AccessTechnology> getAccessTechnology() {
    return Optional.ofNullable(accessTechnology);
  }

  public Optional<Byte> getRoutingAreaCode() {
    return Optional.ofNullable(routingAreaCode);
  }

  public Optional<Integer> getCauseType() {
    return Optional.ofNullable(causeType);
  }

  public Optional<Integer> getRejectCause() {
    return Optional.ofNullable(rejectCause);
  }

  public Optional<String> getActiveTime() {
    return Optional.ofNullable(activeTime);
  }

  public Optional<String> getPeriodicRau() {
    return Optional.ofNullable(periodicRau);
  }

  public Optional<String> getGprsReadyTimer() {
    return Optional.ofNullable(gprsReadyTimer);
  }
}
