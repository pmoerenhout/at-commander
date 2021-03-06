package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.module._3gpp.types.PdpAddress;

public class PdpAddressResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+CGPADDR: (\\d),\"(.*)\"$");

  private PdpAddress[] pdpAddresses;

  public PdpAddressResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final ArrayList<PdpAddress> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (final String line : informationalText) {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final int cid = Integer.parseInt(m.group(1));
        final String address = m.group(2);
        arrayList.add(new PdpAddress(cid, address));
      } else {
        throw createParseException(line);
      }
    }
    pdpAddresses = arrayList.toArray(new PdpAddress[arrayList.size()]);
  }

  public PdpAddress[] getPdpAddresses() {
    return pdpAddresses;
  }

}
