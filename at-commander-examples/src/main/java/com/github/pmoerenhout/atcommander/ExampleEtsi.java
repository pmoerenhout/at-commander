package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_RTSCTS_IN;
import static jssc.SerialPort.FLOWCONTROL_RTSCTS_OUT;

import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import com.github.pmoerenhout.atcommander.module._3gpp.EtsiModem;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleEtsi {

  public static void main(String[] arg) {
    log.info("Test ETSI modem");

    final EtsiModem modem = new EtsiModem(
        new JsscSerial("/dev/tty.usbserial-A1056QHL", 115200, FLOWCONTROL_RTSCTS_IN | FLOWCONTROL_RTSCTS_OUT,
            new UnsolicitedCallback()));
    try {
      modem.init();
      log.info("ETSI modem via jSSC is initialized");
      modem.getAttention();
      modem.close();
      log.info("ETSI modem via jSSC is closed");
    } catch (final Exception e) {
      log.error("The modem had an error", e);
    }
  }

}
