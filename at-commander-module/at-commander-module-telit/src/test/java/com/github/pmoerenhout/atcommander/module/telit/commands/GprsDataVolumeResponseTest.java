package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class GprsDataVolumeResponseTest extends BaseCommandTest {

  @Test
  public void testGprsDataVolumeResponse() throws Exception {
    final AtResponse response = createOkAtResponse("#GDATAVOL: 2,729407,11,729418");

    final GprsDataVolumeResponse gprsDataVolumeResponse = new GprsDataVolumeResponse(response);

    assertEquals(2, gprsDataVolumeResponse.getGprsDataVolumes()[0].getCid());
    assertEquals(729407, gprsDataVolumeResponse.getGprsDataVolumes()[0].getTotal());
    assertEquals(11, gprsDataVolumeResponse.getGprsDataVolumes()[0].getSent());
    assertEquals(729418, gprsDataVolumeResponse.getGprsDataVolumes()[0].getReceived());
  }

}