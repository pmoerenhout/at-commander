package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class ImsiResponseTest extends BaseCommandTest {
  @Test
  public void test_imsi() throws Exception {
    final AtResponse response = createOkAtResponse("20404001234567");

    final ImsiResponse imsiResponse = new ImsiResponse(response);

    assertEquals("20404001234567", imsiResponse.getImsi());
  }
}