package org.atcommander;

import static jssc.SerialPort.FLOWCONTROL_NONE;

import org.atcommander.jssc.JsscSerial;
import org.atcommander.module.v250.V250;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleV250 {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleV250.class);

  public static void main(String[] arg) {
    LOG.info("Test V.250 interface");

    final V250 modem = new V250(new JsscSerial("/dev/tty.usbserial-A40090EW", 9600, FLOWCONTROL_NONE, new UnsolicitedCallback()));
    //final V250 modem = new V250(new JsscSerial("/dev/tty.usbserial-00101414B", 115200, FLOWCONTROL_RTSCTS_IN|FLOWCONTROL_RTSCTS_OUT, new UnsolicitedCallback()));

    try {
      modem.init();
      LOG.info("Is the modem responsive? {}", modem.isResponsive());
      modem.setVerbose(1);
      modem.close();
    } catch (final Exception e) {
      LOG.error("The modem had an error", e);
    }

  }

}
