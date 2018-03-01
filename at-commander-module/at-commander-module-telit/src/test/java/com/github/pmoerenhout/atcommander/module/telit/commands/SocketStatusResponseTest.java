package com.github.pmoerenhout.atcommander.module.telit.commands;


import static org.junit.Assert.assertEquals;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class SocketStatusResponseTest extends BaseCommandTest{

  @Test
  public void testSocketStatusResponse() {

    // #SS: 5,2,10.141.247.132,7,195.8.209.155,7
    final AtResponse response = createOkAtResponse( "#SS: 5,2,10.141.247.132,7,195.8.209.155,7");

    final SocketStatusResponse socketStatusResponse = new SocketStatusResponse(response);

    assertEquals(1, socketStatusResponse.getSocketStatuses().length);
    assertEquals(5, socketStatusResponse.getSocketStatuses()[0].getSocketId());
    assertEquals("10.141.247.132", socketStatusResponse.getSocketStatuses()[0].getLocalIpAddress());
    assertEquals(new Integer(7), socketStatusResponse.getSocketStatuses()[0].getLocalPort());
    assertEquals("195.8.209.155", socketStatusResponse.getSocketStatuses()[0].getRemoteIpAddress());
    assertEquals(new Integer(7), socketStatusResponse.getSocketStatuses()[0].getRemotePort());
  }

}