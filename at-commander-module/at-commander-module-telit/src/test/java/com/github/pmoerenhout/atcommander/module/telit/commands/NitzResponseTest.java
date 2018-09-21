package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class NitzResponseTest extends BaseCommandTest {

  @Test
  public void test_nitz_read() throws Exception {
    final NitzResponse nitzResponse = new NitzResponse();
    nitzResponse.parseSolicited(createOkAtResponse("#NITZ: 1,2"));

    assertEquals(1, nitzResponse.getValue());
    assertEquals(2, nitzResponse.getMode());
  }

}