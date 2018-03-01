package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_RTSCTS_IN;
import static jssc.SerialPort.FLOWCONTROL_RTSCTS_OUT;

import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import com.github.pmoerenhout.atcommander.module._3gpp.EtsiModem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleEtsi {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleEtsi.class);

  public static void main(String[] arg) {
    LOG.info("Test ETSI modem");

    final EtsiModem modem = new EtsiModem(
        new JsscSerial("/dev/tty.usbserial-A1056QHL", 115200, FLOWCONTROL_RTSCTS_IN | FLOWCONTROL_RTSCTS_OUT,
            new UnsolicitedCallback()));
    try {
      modem.init();
      LOG.info("ETSI modem via jSSC is initialized");
      modem.getAttention();
      modem.close();
      LOG.info("ETSI modem via jSSC is closed");
    } catch (final Exception e) {
      LOG.error("The modem had an error", e);
    }
  }

}
