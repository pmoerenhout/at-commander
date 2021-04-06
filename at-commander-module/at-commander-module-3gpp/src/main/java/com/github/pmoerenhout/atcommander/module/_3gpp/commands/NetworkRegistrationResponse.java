package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class NetworkRegistrationResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CREG: (\\d),(\\d)(,\"(.*)\",\"(.*)\"(,(\\d))?)?$");

  protected int mode;
  protected RegistrationState registrationState;
  protected Integer lac;
  protected Integer cellId;
  protected AccessTechnology accessTechnology;

  public NetworkRegistrationResponse() {
  }

  public NetworkRegistrationResponse(final AtResponse response) {
    parseSolicited(response);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        mode = Integer.parseInt(m.group(1));
        registrationState = RegistrationState.fromString(m.group(2));
        if (m.group(3) != null) {
          lac = Integer.valueOf(Integer.parseUnsignedInt(m.group(4), 16));
          cellId = Integer.valueOf(Integer.parseUnsignedInt(m.group(5), 16));
          if (m.group(6) != null) {
            accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m.group(7)));
          }
        }
//        final String[] tokens = Util.tokenize(m.group(1));
//        mode = Integer.parseInt(tokens[0]);
//        registrationState = RegistrationState.fromString(tokens[1]);
//        if (tokens.length > 2) {
//          lac = Integer.parseUnsignedInt(tokens[2], 16);
//          cellId = Integer.parseUnsignedInt(tokens[3], 16);
//          if (tokens.length > 4) {
//            accessTechnology = AccessTechnology.fromInt(Integer.parseUnsignedInt(tokens[4], 16));
//          }
//        }
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getMode() {
    return mode;
  }

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
        Objects.equals(cellId, that.cellId) &&
        Objects.equals(accessTechnology, that.accessTechnology);
  }

  @Override
  public int hashCode() {
    return Objects.hash(mode, registrationState, lac, cellId, accessTechnology);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("NetworkRegistrationResponse{");
    sb.append("mode=").append(mode);
    sb.append(", registrationState=").append(registrationState);
    sb.append(", lac=").append(lac);
    sb.append(", cellId=").append(cellId);
    sb.append(", accessTechnology=").append(accessTechnology);
    sb.append('}');
    return sb.toString();
  }
}
