package com.github.pmoerenhout.atcommander.module._3gpp.unsolicited;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.api.annotation.Unsolicited;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

@Unsolicited
public class NetworkRegistrationUnsolicited extends BaseResponse implements UnsolicitedResponse {

  // +CREG: <stat> when <n> = 1
  // +CREG: <stat>[,<lac>,<ci>] when <n> = 2
  // +CREG: <stat>[,<lac>,<ci>,<act>] when <n> = 3
  // public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CREG: (\\d)(,\"(.*)\",\"(.*)\"(,(\\d)(,(\\d))?)?)?$");
  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CREG: (\\d)(,\"(.*)\",\"(.*)\"(,(\\d)(,(\\d)(,(\\d*))?)?)?)?$");

  private RegistrationState registrationState;
  private Integer lac;
  private Integer cellId;
  private AccessTechnology accessTechnology;
  private Integer causeType;
  private Integer rejectCause;

  public NetworkRegistrationUnsolicited() {
  }

  public Pattern getPattern() {
    return UNSOLICITED_PATTERN;
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
              causeType = Integer.valueOf(m.group(8));
              if (m.group(9) != null) {
                rejectCause = Integer.valueOf(m.group(10));
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

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final NetworkRegistrationUnsolicited that = (NetworkRegistrationUnsolicited) o;
    return registrationState == that.registrationState &&
        Objects.equals(lac, that.lac) &&
        Objects.equals(cellId, that.cellId) &&
        Objects.equals(accessTechnology, that.accessTechnology) &&
        Objects.equals(causeType, that.causeType) &&
        Objects.equals(rejectCause, that.rejectCause);
  }

  @Override
  public int hashCode() {
    return Objects.hash(registrationState, lac, cellId, accessTechnology, causeType, rejectCause);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("NetworkRegistrationResponse{");
    sb.append(", registrationState=").append(registrationState);
    sb.append(", lac=").append(lac);
    sb.append(", cellId=").append(cellId);
    sb.append(", accessTechnology=").append(accessTechnology);
    sb.append(", causeType=").append(causeType);
    sb.append(", rejectCause=").append(rejectCause);
    sb.append('}');
    return sb.toString();
  }
}
