package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class GprsActStatusResponseTest extends BaseCommandTest {

  @Test
  public void testCgactActive() throws Exception {
    final AtResponse response = createOkAtResponse("+CGACT: 5,1");

    final GprsActStatusResponse gprsActStatusResponse = new GprsActStatusResponse(response);

    assertEquals(5, gprsActStatusResponse.getGprsActives()[0].getCid());
    assertEquals(true, gprsActStatusResponse.getGprsActives()[0].isActive());
  }

  @Test
  public void testCgactDeactive() throws Exception {
    final AtResponse response = createOkAtResponse("+CGACT: 4,0");

    final GprsActStatusResponse gprsActStatusResponse = new GprsActStatusResponse(response);

    assertEquals(4, gprsActStatusResponse.getGprsActives()[0].getCid());
    assertEquals(false, gprsActStatusResponse.getGprsActives()[0].isActive());
  }
}