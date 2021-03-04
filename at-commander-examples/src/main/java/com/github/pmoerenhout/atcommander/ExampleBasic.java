package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_XONXOFF_IN;
import static jssc.SerialPort.FLOWCONTROL_XONXOFF_OUT;

import com.github.pmoerenhout.atcommander.basic.Basic;
import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ExampleBasic {

  public static void main(String[] arg) {
    log.info("Test modem which only understand AT");

    //final Basic modem = new Basic(new JsscSerial("/dev/tty.usbserial-A1056QHL", 9600, FLOWCONTROL_NONE, callback));
    final Basic modem = new Basic(
        new JsscSerial("/dev/tty.usbserial-A1056QHL", 9600, FLOWCONTROL_XONXOFF_IN | FLOWCONTROL_XONXOFF_OUT,
            new UnsolicitedCallback()));

    try {
      modem.init();
      log.info("Is the modem responsive? {}", modem.isResponsive());
      // Send a simple AT
      modem.getSimpleCommand("AT").set();
      // Send a lot of AT commands
      for (int i = 0; i < 1000; i++) {
        modem.getAttention();
      }
      modem.close();
    } catch (final Exception e) {
      log.error("The modem had an error", e);
    }

  }

}
