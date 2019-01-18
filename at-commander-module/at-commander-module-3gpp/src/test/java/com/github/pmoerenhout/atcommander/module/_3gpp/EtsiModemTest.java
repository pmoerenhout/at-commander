package com.github.pmoerenhout.atcommander.module._3gpp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.pmoerenhout.atcommander.basic.SerialMock;
import com.github.pmoerenhout.atcommander.basic.exceptions.ErrorException;

@RunWith(MockitoJUnitRunner.class)
public class EtsiModemTest {

  private EtsiModem etsiModem;
  private SerialMock serial;

  @Before
  public void setUp() throws Exception {
    serial = new SerialMock();
    serial.setMockResponses("AT", new String[]{ "OK" });
    serial.setMockResponses("ATE0", new String[]{ "OK" });
    etsiModem = new EtsiModem(serial);
    etsiModem.init();
  }

  @Test
  public void test_at() throws Exception {
    etsiModem.getAttention();
  }

  @Test
  public void test_at_e1() throws Exception {
    serial.setMockResponses("ATE1", new String[]{ "OK" });
    etsiModem.getSimpleCommand("ATE1").set();
  }

  @Test(expected = ErrorException.class)
  public void test_error() throws Exception {
    serial.setMockResponses("ATE2", new String[]{ "ERROR" });
    etsiModem.getSimpleCommand("ATE2").set();
  }

  @Test
  public void test_is_responsive() throws Exception {
    etsiModem.isResponsive();
  }

  @Test
  public void test_gprs_unsolicited() throws Exception {
    serial.setMockResponses("AT+CGATT=1", new String[]{
        "+CGREG: 1,\"00DE\",\"FD4F\",0,\"01\"",
        "+CGREG: 1,\"00DE\",\"FD4F\",0,\"01\"",
        "OK" });
    etsiModem.setGprsAttach(true, 60000);
  }

}