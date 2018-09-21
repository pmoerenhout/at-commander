package com.github.pmoerenhout.atcommander.module.telit.commands;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketStatus;

public class SocketStatusResponseTest extends BaseCommandTest {

  @Test
  public void test_socket_status_response() {

    // #SS: 5,2,10.141.247.132,7,195.8.209.155,7
    final AtResponse response = createOkAtResponse("#SS: 5,2,10.141.247.132,7,195.8.209.155,7");

    final SocketStatusResponse socketStatusResponse = new SocketStatusResponse(response);

    final SocketStatus socketStatus = socketStatusResponse.getSocketStatuses()[0];
    assertEquals(1, socketStatusResponse.getSocketStatuses().length);
    assertEquals(5, socketStatus.getSocketId());
    assertEquals("10.141.247.132", socketStatus.getLocalIpAddress());
    assertEquals(Integer.valueOf(7), socketStatus.getLocalPort());
    assertEquals("195.8.209.155", socketStatus.getRemoteIpAddress());
    assertEquals(Integer.valueOf(7), socketStatus.getRemotePort());
  }

}