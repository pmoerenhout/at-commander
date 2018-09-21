package com.github.pmoerenhout.atcommander.module.telit.unsolicited;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

public class SocketRingUnsolicitedTest {

  @Test
  public void test_socket_ring_simple() {
    // SRING: 6'
    final SocketRingUnsolicited socketRingUnsolicited = new SocketRingUnsolicited();
    socketRingUnsolicited.parseUnsolicited(Collections.singletonList("SRING: 6"));

    assertEquals(6, socketRingUnsolicited.getSocketId());
  }

  @Test
  public void test_socket_ring() {
    // SRING: "195.8.209.155",7,6,69,0,2015-06-08T09:45:49.0512015-06-08T09:45:49.0532015-06-08T09:45:49.054'
    final String line = "SRING: \"195.8.209.155\",7,6,69,0,2015-06-08T09:45:49.0512015-06-08T09:45:49.0532015-06-08T09:45:49.054";
    final SocketRingUnsolicited socketRingUnsolicited = new SocketRingUnsolicited();
    socketRingUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals("195.8.209.155", socketRingUnsolicited.getSourceAddress());
    assertEquals(Integer.valueOf(7), socketRingUnsolicited.getSourcePort());
    assertEquals(6, socketRingUnsolicited.getSocketId());
    assertEquals(Integer.valueOf(69), socketRingUnsolicited.getReceivedData());
    assertEquals(Integer.valueOf(0), socketRingUnsolicited.getDataLeft());
    assertEquals("2015-06-08T09:45:49.0512015-06-08T09:45:49.0532015-06-08T09:45:49.054", socketRingUnsolicited.getData());
  }

  @Test
  public void test_socket_ring_2() {
    // SRING: 5,69,5544503B343833393B36653833613436642D356530342D343736312D613132362D3063663732333162393138393B323031352D30362D30385431353A33393A31342E373633
    final String line = "SRING: 5,69,5544503B343833393B36653833613436642D356530342D343736312D613132362D3063663732333162393138393B323031352D30362D30385431353A33393A31342E373633";
    final SocketRingUnsolicited socketRingUnsolicited = new SocketRingUnsolicited();
    socketRingUnsolicited.parseUnsolicited(Collections.singletonList(line));

    assertEquals(5, socketRingUnsolicited.getSocketId());
    assertEquals(Integer.valueOf(69), socketRingUnsolicited.getReceivedData());
    assertEquals(
        "5544503B343833393B36653833613436642D356530342D343736312D613132362D3063663732333162393138393B323031352D30362D30385431353A33393A31342E373633",
        socketRingUnsolicited.getData());
  }

  @Test
  public void test_socket_ring_3() {
    // SRING: 6,69
    final SocketRingUnsolicited socketRingUnsolicited = new SocketRingUnsolicited();
    socketRingUnsolicited.parseUnsolicited(Collections.singletonList("SRING: 6,69"));

    assertEquals(6, socketRingUnsolicited.getSocketId());
    assertEquals(Integer.valueOf(69), socketRingUnsolicited.getReceivedData());
  }
}