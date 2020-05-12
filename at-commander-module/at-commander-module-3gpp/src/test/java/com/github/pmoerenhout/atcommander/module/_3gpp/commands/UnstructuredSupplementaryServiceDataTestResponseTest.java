package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class UnstructuredSupplementaryServiceDataTestResponseTest extends BaseCommandTest {

  @Test
  public void test_cusd_test() throws Exception {
    final AtResponse response = createOkAtResponse("+CUSD: (0-2)");

    final UnstructuredSupplementaryServiceDataTestResponse ussdResponse = new UnstructuredSupplementaryServiceDataTestResponse(response);

    assertEquals(3, ussdResponse.getTypes().size());

  }
}