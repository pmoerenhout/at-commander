package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_NONE;

import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import com.github.pmoerenhout.atcommander.module.v250.V250;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleV250 {

  public static void main(String[] arg) {
    log.info("Test V.250 interface");

    final V250 modem = new V250(new JsscSerial("/dev/tty.usbserial-A40090EW", 9600, FLOWCONTROL_NONE, new UnsolicitedCallback()));
    //final V250 modem = new V250(new JsscSerial("/dev/tty.usbserial-00101414B", 115200, FLOWCONTROL_RTSCTS_IN|FLOWCONTROL_RTSCTS_OUT, new UnsolicitedCallback()));

    try {
      modem.init();
      log.info("Is the modem responsive? {}", modem.isResponsive());
      modem.setVerbose(1);
      modem.close();
    } catch (final Exception e) {
      log.error("The modem had an error", e);
    }

  }

}
