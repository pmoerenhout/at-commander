package org.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.module.telit.types.NetworkDns;
import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.basic.commands.Response;

public class NetworkDnsResponse extends BaseResponse implements Response {

  // #NWDNS: 1,"8.8.8.8","8.8.4.4
  private final static Pattern PATTERN = Pattern.compile("^#NWDNS: ([1-6]),\"([0-9.]*)\",\"([0-9.]*)\"$");

  private NetworkDns[] networkDnses;

  public NetworkDnsResponse(final AtResponse s) {
    this.parse(s);
  }

  public void parse(final AtResponse response) {
    final ArrayList<NetworkDns> arrayList = new ArrayList<>();
    final List<String> informationalText = response.getInformationalText();
    for (final String line : informationalText) {
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final int cid = Integer.parseInt(m.group(1));
        final String primaryDns = m.group(2);
        final String secondaryDns = m.group(3);
        arrayList.add(new NetworkDns(cid, primaryDns, secondaryDns));
      }
      else {
        throw createParseException(line);
      }
    }
    networkDnses = arrayList.toArray(new NetworkDns[arrayList.size()]);
  }

  public NetworkDns[] getNetworkDnses() {
    return networkDnses;
  }
}
