package org.atcommander;

import static jssc.SerialPort.FLOWCONTROL_XONXOFF_IN;
import static jssc.SerialPort.FLOWCONTROL_XONXOFF_OUT;

import org.atcommander.basic.Basic;
import org.atcommander.jssc.JsscSerial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleBasic {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleBasic.class);

  public static void main(String[] arg) {
    LOG.info("Test modem which only understand AT");

    //final Basic modem = new Basic(new JsscSerial("/dev/tty.usbserial-A1056QHL", 9600, FLOWCONTROL_NONE, callback));
    final Basic modem = new Basic(
        new JsscSerial("/dev/tty.usbserial-A1056QHL", 9600, FLOWCONTROL_XONXOFF_IN | FLOWCONTROL_XONXOFF_OUT,
            new UnsolicitedCallback()));

    try {
      modem.init();
      LOG.info("Is the modem responsive? {}", modem.isResponsive());
      // Send a simple AT
      modem.getSimpleCommand("AT").set();
      // Send a lot of AT commands
      for (int i = 0; i < 1000; i++) {
        modem.getAttention();
      }
      modem.close();
    } catch (final Exception e) {
      LOG.error("The modem had an error", e);
    }

  }

}
