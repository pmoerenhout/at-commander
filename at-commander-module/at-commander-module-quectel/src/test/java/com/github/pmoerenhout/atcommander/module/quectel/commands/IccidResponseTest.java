package com.github.pmoerenhout.atcommander.module.quectel.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class IccidResponseTest extends BaseCommandTest {

  @Test
  public void testIccid() throws Exception {
    final AtResponse response = createOkAtResponse("+QCCID: 8901260886258128535F");

    final IccidResponse iccidResponse = new IccidResponse(response);

    assertEquals("8901260886258128535", iccidResponse.getIccid());
  }
}