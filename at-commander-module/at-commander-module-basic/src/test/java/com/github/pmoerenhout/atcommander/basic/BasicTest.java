package com.github.pmoerenhout.atcommander.basic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.pmoerenhout.atcommander.basic.exceptions.ErrorException;

@RunWith(MockitoJUnitRunner.class)
public class BasicTest {

  private Basic basic;
  private SerialMock serial;

  @Before
  public void setUp() throws Exception {
    serial = new SerialMock();
    serial.setMockResponses("AT", new String[]{"OK"});
    basic = new Basic(serial);
    basic.init();
  }

  @Test
  public void test_at() throws Exception {
    basic.getAttention();
  }

  @Test
  public void test_at_e1() throws Exception {
    serial.setMockResponses("ATE1", new String[]{"OK"});
    basic.getSimpleCommand("ATE1").set();
  }

  @Test(expected = ErrorException.class)
  public void test_error() throws Exception {
    serial.setMockResponses("ATE2", new String[]{"ERROR"});
    basic.getSimpleCommand("ATE2").set();
  }

  @Test
  public void test_is_responsive() throws Exception {
    basic.isResponsive();
  }
}