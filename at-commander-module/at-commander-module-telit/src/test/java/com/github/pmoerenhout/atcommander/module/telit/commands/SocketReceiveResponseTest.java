package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class SocketReceiveResponseTest extends BaseCommandTest {

  @Test
  public void testSocketReceive() throws Exception {

    final List<String> t = new ArrayList<>();
    t.add("#SRECV: 5,19");
    t.add("5544503B35353731393B65613338356331392D");
    t.add("OK");
    final AtResponse response = createOkAtResponse(t);

    SocketReceiveResponse socketReceiveResponse = new SocketReceiveResponse(response);

    assertEquals("5544503B35353731393B65613338356331392D", socketReceiveResponse.getData());
  }
}