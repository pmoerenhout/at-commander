package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.WirelessNetwork;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class WirelessNetworkStatusResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+WS46: (\\d*)$");

  private WirelessNetwork wirelessNetwork;

  public WirelessNetworkStatusResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
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
