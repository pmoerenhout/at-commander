package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class SimPresenceStatusResponseTest extends BaseCommandTest {

  @Test
  public void test_simpr_unsolicited() {
    final String line = "#SIMPR: 0,1";

    final SimPresenceStatusResponse simPresenceStatusResponse = new SimPresenceStatusResponse(line);

    assertNull(simPresenceStatusResponse.getSimPresences().get(0).getMode());
    assertEquals(false, simPresenceStatusResponse.getSimPresences().get(0).isRemote());
    assertEquals(true, simPresenceStatusResponse.getSimPresences().get(0).isInserted());
  }

  @Test
  public void test_simpr_multiple() {
    final AtResponse response = createAtResponse(new String[]{ "#SIMPR: 1,0,1", "#SIMPR: 0,1,0", "OK" });

    final SimPresenceStatusResponse simPresenceStatusResponse = new SimPresenceStatusResponse(response);

    System.out.println(simPresenceStatusResponse);

    assertEquals(new Integer(1), simPresenceStatusResponse.getSimPresences().get(0).getMode());
    assertEquals(false, simPresenceStatusResponse.getSimPresences().get(0).isRemote());
    assertEquals(true, simPresenceStatusResponse.getSimPresences().get(0).isInserted());

    assertEquals(new Integer(0), simPresenceStatusResponse.getSimPresences().get(1).getMode());
    assertEquals(true, simPresenceStatusResponse.getSimPresences().get(1).isRemote());
    assertEquals(false, simPresenceStatusResponse.getSimPresences().get(1).isInserted());
  }
}