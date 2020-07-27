package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class PhoneActivityStatusResponseTest extends BaseCommandTest {

  @Test
  public void test_phone_activity_status() throws Exception {
    final AtResponse response = createOkAtResponse("+CPAS: 2");

    final PhoneActivityStatusResponse phoneActivityStatusResponse = new PhoneActivityStatusResponse(response);

    assertEquals(2, phoneActivityStatusResponse.getStatus());
  }

}