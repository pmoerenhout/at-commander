package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.api.UnsolicitedResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class GprsNetworkRegistrationResponse extends com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsNetworkRegistrationResponse
    implements UnsolicitedResponse {

  // +CGREG: 1,"00DE","FD4F",0,"01"
  public static final Pattern UNSOLICITED_PATTERN_1 = Pattern.compile("^\\+CGREG: (\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\",(\\d),\"(.*)\"$");
  // HE910
  // +CGREG: 2,5,"0CBC","D1A7",0,"01"
  public static final Pattern UNSOLICITED_PATTERN_2 = Pattern.compile("^\\+CGREG: (\\d),(\\d),\"([0-9A-F]*)\",\"([0-9A-F]*)\",(\\d),\"(.*)\"$");

  private Integer rac;

  public GprsNetworkRegistrationResponse() {
  }

  public void parseUnsolicited(final List<String> lines) {
    parse(lines.get(0));
  }

  protected void parse(final String line) {
    final Matcher m4 = UNSOLICITED_PATTERN_1.matcher(line);
    if (m4.find()) {
      registrationState = RegistrationState.fromString(m4.group(1));
      lac = Integer.parseUnsignedInt(m4.group(2), 16);
      cellId = Integer.parseUnsignedInt(m4.group(3), 16);
      accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m4.group(4)));
      rac = Integer.parseUnsignedInt(m4.group(5), 16);
      return;
    }
    final Matcher m2 = UNSOLICITED_PATTERN_2.matcher(line);
    if (m2.find()) {
      mode = Integer.parseInt(m2.group(1));
      registrationState = RegistrationState.fromString(m2.group(2));
      lac = Integer.parseUnsignedInt(m2.group(3), 16);
      cellId = Integer.parseUnsignedInt(m2.group(4), 16);
      accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m2.group(5)));
      rac = Integer.parseUnsignedInt(m2.group(6), 16);
      return;
    }
    super.parse(line);
  }

  public Integer getRac() {
    return rac;
  }
}
