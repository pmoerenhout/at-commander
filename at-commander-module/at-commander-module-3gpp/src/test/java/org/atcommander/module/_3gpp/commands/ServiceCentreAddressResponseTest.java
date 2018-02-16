package org.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class ServiceCentreAddressResponseTest extends BaseCommandTest {
  @Test
  public void testServiceCentreAddressResponse() throws Exception {
    final AtResponse response = createOkAtResponse("+CSCA: \"+31654000000\",145");

    final ServiceCentreAddressResponse serviceCentreAddressResponse = new ServiceCentreAddressResponse(response);

    assertEquals("+31654000000", serviceCentreAddressResponse.getNumber());
    assertEquals(145, serviceCentreAddressResponse.getType());
  }
}