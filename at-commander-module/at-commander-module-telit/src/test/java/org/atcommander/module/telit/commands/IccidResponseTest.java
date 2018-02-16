package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class IccidResponseTest extends BaseCommandTest {

  @Test
  public void testIccid() throws Exception {
    final AtResponse response = createOkAtResponse("#CCID: 8903213243545678129");

    final IccidResponse iccidResponse = new IccidResponse(response);

    assertEquals("8903213243545678129", iccidResponse.getIccid());
  }
}