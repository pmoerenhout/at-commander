package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class NetworkDnsResponseTest extends BaseCommandTest {

  @Test
  public void testNetworkDns() {
    final AtResponse response = createOkAtResponse("#NWDNS: 2,\"8.8.8.8\",\"1.2.3.4\"");

    final NetworkDnsResponse networkDnsResponse = new NetworkDnsResponse(response);

    assertEquals(1, networkDnsResponse.getNetworkDnses().length);
    assertEquals(2, networkDnsResponse.getNetworkDnses()[0].getCid());
    assertEquals("8.8.8.8", networkDnsResponse.getNetworkDnses()[0].getPrimaryDnsAddress());
    assertEquals("1.2.3.4", networkDnsResponse.getNetworkDnses()[0].getSecondaryDnsAddress());

  }
}