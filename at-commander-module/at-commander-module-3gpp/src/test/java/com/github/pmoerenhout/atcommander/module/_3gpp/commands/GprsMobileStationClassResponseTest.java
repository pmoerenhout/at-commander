package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class GprsMobileStationClassResponseTest extends BaseCommandTest {

  @Test
  public void test_gprs_class_response_a() throws Exception {
    final AtResponse response = createOkAtResponse("+CGCLASS: \"A\"");

    final GprsMobileStationClassResponse gprsMobileStationClassResponse = new GprsMobileStationClassResponse(response);

    assertEquals("A", gprsMobileStationClassResponse.getClazz());
  }

  @Test
  public void test_gprs_class_response_cg() throws Exception {
    final AtResponse response = createOkAtResponse("+CGCLASS: \"CG\"");

    final GprsMobileStationClassResponse gprsMobileStationClassResponse = new GprsMobileStationClassResponse(response);

    assertEquals("CG", gprsMobileStationClassResponse.getClazz());
  }

}