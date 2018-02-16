package org.atcommander.module.telit.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.api.UnsolicitedResponse;
import org.atcommander.module.v250.enums.PacketServiceNetworkType;
import org.atcommander.basic.exceptions.ParseException;
import org.atcommander.basic.commands.BaseResponse;

public class PacketServiceNetworkTypeResponse extends BaseResponse implements UnsolicitedResponse {

  public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^#PSNT: ([0-4])$");

  private PacketServiceNetworkType type;

  public PacketServiceNetworkTypeResponse(final String s) {
    parse(s);
  }

  public void parse(final String line) {
    final Matcher m = UNSOLICITED_PATTERN.matcher(line);
    if (m.find()) {
      type = PacketServiceNetworkType.fromInt(Integer.parseInt(m.group(1)));
      return;
    }
    throw new ParseException("Could not parse response: " + line);
  }

  public PacketServiceNetworkType getType() {
    return type;
  }


}
