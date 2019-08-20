package com.github.pmoerenhout.atcommander.module.quectel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.github.pmoerenhout.atcommander.basic.SerialMock;

@RunWith(MockitoJUnitRunner.class)
public class QuectelModemTest {

  private QuectelModem quectelModem;
  private SerialMock serial;

  @Before
  public void setUp() throws Exception {
    serial = new SerialMock();
    serial.setMockResponses("AT", new String[]{ "OK" });
    serial.setMockResponses("ATE0", new String[]{ "OK" });
    serial.setMockResponses("AT+CGMR", new String[]{ "0.0.0", "OK" });
    quectelModem = new QuectelModem(serial);
    quectelModem.init();
  }

  @Test
  public void test_at() throws Exception {
    quectelModem.getAttention(1000);
  }

  @Test
  public void test_is_responsive() throws Exception {
    quectelModem.isResponsive();
  }

  @Test
  public void test_gprs_unsolicited() throws Exception {
    serial.setMockResponses("AT+CGATT=1", new String[]{
        "+CGREG: 1,\"00DE\",\"FD4F\",0,\"01\"",
        "+CGREG: 1,\"00DE\",\"FD4F\",0,\"01\"",
        "OK" });
    quectelModem.setGprsAttach(true, 60000);
  }

  @Test
  public void test_ccid() throws Exception {
    serial.setMockResponses("AT+QCCID", new String[]{ "+QCCID: 8901260886258128535F", "OK" });
    final String iccid = quectelModem.getIntegratedCircuitCardIdentification();
    Assert.assertEquals("8901260886258128535", iccid);
  }
}