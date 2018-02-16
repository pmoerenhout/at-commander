package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.basic.commands.Response;
import org.atcommander.module._3gpp.RegistrationState;
import org.atcommander.module.v250.enums.AccessTechnology;

public class NetworkRegistrationResponse extends org.atcommander.module._3gpp.commands.NetworkRegistrationResponse
    implements Response, UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CREG: (\\d),\"(.*)\",\"(.*)\",(\\d)$");

  private static final Pattern PATTERN = Pattern.compile("^\\+CREG: (\\d),(\\d),\"(.*)\",\"(.*)\",(\\d)$");

  private AccessTechnology accessTechnology;

  public NetworkRegistrationResponse(final String line) {
    parse(line);
  }

  public NetworkRegistrationResponse(final AtResponse response) {
    parse(response);
  }

  public void parse(final String line) {
    final Matcher m4 = UNSOLICITED_PATTERN.matcher(line);
    if (m4.find()) {
      registrationState = RegistrationState.fromString(m4.group(1));
      lac = Integer.parseUnsignedInt(m4.group(2), 16);
      cellId = Integer.parseUnsignedInt(m4.group(3), 16);
      accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m4.group(4)));
      return;
    }
    super.parse(line);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m5 = PATTERN.matcher(line);
      if (m5.find()) {
        mode = Integer.parseInt(m5.group(1));
        registrationState = RegistrationState.fromString(m5.group(2));
        lac = Integer.parseUnsignedInt(m5.group(3), 16);
        cellId = Integer.parseUnsignedInt(m5.group(4), 16);
        accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m5.group(5)));
        return;
      }
      super.parse(response);
    } else {
      throw createParseException(response);
    }
  }

  public AccessTechnology getAccessTechnology() {
    return accessTechnology;
  }
}