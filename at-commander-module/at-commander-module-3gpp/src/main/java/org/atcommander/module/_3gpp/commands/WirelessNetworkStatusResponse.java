package org.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.AtResponse;
import org.atcommander.module.v250.enums.WirelessNetwork;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class WirelessNetworkStatusResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+WS46: (\\d*)$");

  private WirelessNetwork wirelessNetwork;

  public WirelessNetworkStatusResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final int wirelessNetworkNumber = Integer.parseInt(m.group(1));
        wirelessNetwork = WirelessNetwork.fromInt(wirelessNetworkNumber);
        // LOG.debug("WS46: {} ({})", wirelessNetwork, wirelessNetworkNumber);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public WirelessNetwork getWirelessNetwork() {
    return wirelessNetwork;
  }
}
