package com.github.pmoerenhout.atcommander.module.telit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.pmoerenhout.atcommander.basic.SerialMock;

@RunWith(MockitoJUnitRunner.class)
public class TelitModemTest {

  private TelitModem telitModem;
  private SerialMock serial;

  @Before
  public void setUp() throws Exception {
    serial = new SerialMock();
    serial.setMockResponses("AT", new String[]{ "OK" });
    serial.setMockResponses("ATE0", new String[]{ "OK" });
    serial.setMockResponses("AT+CGMR", new String[]{ "0.0.0", "OK" });
    telitModem = new TelitModem(serial);
    telitModem.init();
  }

  @Test
  public void test_at() throws Exception {
    telitModem.getAttention();
  }

  @Test
  public void test_is_responsive() throws Exception {
    telitModem.isResponsive();
  }

  @Test
  public void test_gprs_unsolicited() throws Exception {
    serial.setMockResponses("AT+CGATT=1", new String[]{
        "+CGREG: 1,\"00DE\",\"FD4F\",0,\"01\"",
        "+CGREG: 1,\"00DE\",\"FD4F\",0,\"01\"",
        "OK" });
    telitModem.setGprsAttach(true, 60000);
  }
}