package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;

public class CallingLineIdentificationPresentationResponseTest extends BaseCommandTest {

  @Test
  public void test_clip_1() throws Exception {
    final AtResponse atResponse = createOkAtResponse("+CLIP: 0,1");

    final CallingLineIdentificationPresentationResponse response = new CallingLineIdentificationPresentationResponse();
    response.parseSolicited(atResponse);

    assertEquals(Boolean.FALSE, response.getCallingLineIndicationPresentation());
    assertEquals(Boolean.TRUE, response.getCallingLineIndicationPresentationProvisioned());
  }

  @Test
  public void test_clip_2() throws Exception {
    final AtResponse atResponse = createOkAtResponse("+CLIP: 1,0");

    final CallingLineIdentificationPresentationResponse response = new CallingLineIdentificationPresentationResponse();
    response.parseSolicited(atResponse);

    assertEquals(Boolean.TRUE, response.getCallingLineIndicationPresentation());
    assertEquals(Boolean.FALSE, response.getCallingLineIndicationPresentationProvisioned());
  }
}