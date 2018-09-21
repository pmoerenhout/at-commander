package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class RestrictedSimAccessResponseTest extends BaseCommandTest {

  @Test
  public void test_restricted_sim_access_response() {
    final AtResponse response = createOkAtResponse("+CRSM: 144,0");

    final RestrictedSimAccessResponse restrictedSimAccessResponse = new RestrictedSimAccessResponse(response);

    assertEquals(0x9000, restrictedSimAccessResponse.getSw());
    assertEquals(144, restrictedSimAccessResponse.getSw1());
    assertEquals(0, restrictedSimAccessResponse.getSw2());
    assertArrayEquals(new byte[]{}, restrictedSimAccessResponse.getData());
  }

  @Test
  public void test_restricted_sim_access_response_with_data() {
    final AtResponse response = createOkAtResponse("+CRSM: 144,0,FFFF");

    final RestrictedSimAccessResponse restrictedSimAccessResponse = new RestrictedSimAccessResponse(response);

    assertEquals(0x9000, restrictedSimAccessResponse.getSw());
    assertEquals(144, restrictedSimAccessResponse.getSw1());
    assertEquals(0, restrictedSimAccessResponse.getSw2());
    assertArrayEquals(new byte[]{ (byte) 0xff, (byte) 0xff }, restrictedSimAccessResponse.getData());
  }
}