package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SocketRingResponseTest {

  @Test
  public void test_socket_ring_simple() {
    // SRING: 1'
    final String line = "SRING: 6";
    final SocketRingResponse socketRingResponse = new SocketRingResponse(line);

    assertEquals(6, socketRingResponse.getSocketId());
  }

  @Test
  public void test_socket_ring() {
    // SRING: "195.8.209.155",7,6,69,0,2015-06-08T09:45:49.0512015-06-08T09:45:49.0532015-06-08T09:45:49.054'
    final String line = "SRING: \"195.8.209.155\",7,6,69,0,2015-06-08T09:45:49.0512015-06-08T09:45:49.0532015-06-08T09:45:49.054";
    final SocketRingResponse socketRingResponse = new SocketRingResponse(line);

    assertEquals("195.8.209.155", socketRingResponse.getSourceAddress());
    assertEquals(new Integer(7), socketRingResponse.getSourcePort());
    assertEquals(6, socketRingResponse.getSocketId());
    assertEquals(new Integer(69), socketRingResponse.getReceivedData());
    assertEquals(new Integer(0), socketRingResponse.getDataLeft());
    assertEquals("2015-06-08T09:45:49.0512015-06-08T09:45:49.0532015-06-08T09:45:49.054", socketRingResponse.getData());
  }

  @Test
  public void test_socket_ring_2() {
    // SRING: 5,69,5544503B343833393B36653833613436642D356530342D343736312D613132362D3063663732333162393138393B323031352D30362D30385431353A33393A31342E373633
    final String line = "SRING: 5,69,5544503B343833393B36653833613436642D356530342D343736312D613132362D3063663732333162393138393B323031352D30362D30385431353A33393A31342E373633";
    final SocketRingResponse socketRingResponse = new SocketRingResponse(line);

    assertEquals(5, socketRingResponse.getSocketId());
    assertEquals(new Integer(69), socketRingResponse.getReceivedData());
    assertEquals(
        "5544503B343833393B36653833613436642D356530342D343736312D613132362D3063663732333162393138393B323031352D30362D30385431353A33393A31342E373633",
        socketRingResponse.getData());
  }

  @Test
  public void test_socket_ring_3() {
    // SRING: 6,69
    final String line = "SRING: 6,69";
    final SocketRingResponse socketRingResponse = new SocketRingResponse(line);

    assertEquals(6, socketRingResponse.getSocketId());
    assertEquals(new Integer(69), socketRingResponse.getReceivedData());
  }
}