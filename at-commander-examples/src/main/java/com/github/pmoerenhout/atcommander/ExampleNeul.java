package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_XONXOFF_IN;
import static jssc.SerialPort.FLOWCONTROL_XONXOFF_OUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import com.github.pmoerenhout.atcommander.module.neul.Neul;
import com.github.pmoerenhout.atcommander.module.neul.commands.SignalQualityResponse;

public class ExampleNeul {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleNeul.class);

  public static void main(String[] arg) {
    LOG.info("Test modem which only understand AT");

    final Neul modem = new Neul(
        new JsscSerial("/dev/tty.usbserial-A1056QHL", 9600, FLOWCONTROL_XONXOFF_IN | FLOWCONTROL_XONXOFF_OUT, new UnsolicitedCallback()));

    try {
      modem.init();
      LOG.info("Is the modem responsive? {}", modem.isResponsive());
      // Send a simple AT
      // modem.getSimpleCommand("AT").set();
      modem.getAttention();
      // Send AT+NRB
      if (false) {
        modem.reboot();
        Thread.sleep(10000);
      }
      modem.getAttention();
      modem.getAttention();

      LOG.info("Manufacturer: {}", getManufacturer(modem));
      LOG.info("Module revision: {}", getRevisionIdentification(modem));
      Thread.sleep(1000);

      modem.getConfig().getItems().forEach(l -> LOG.info("Configuration: {} is {}", l.getLeft(), l.getRight()));
      final SignalQualityResponse signalQualityResponse = modem.getSignalQuality();
      LOG.info("Received signal strength indicator is {} and bit error rate is {}", signalQualityResponse.getRssi(), signalQualityResponse.getBer());
      modem.config("AUTOCONNECT", "FALSE");
      modem.config("CR_0354_0338_SCRAMBLING", "FALSE");
      //modem.config("CR_0859_SI_AVOID", "FALSE");
      // T-Mobile forum
      modem.config("CR_0354_0338_SCRAMBLING", "TRUE");
      modem.config("CR_0859_SI_AVOID", "TRUE");
      modem.getConfig().getItems().forEach(l -> LOG.info("Configuration: {} is {}", l.getLeft(), l.getRight()));
      modem.close();
    } catch (final Exception e) {
      LOG.error("The modem had an error", e);
    }

  }

  private static String getManufacturer(final Neul modem) throws TimeoutException, ResponseException, SerialException, InterruptedException{
    return modem.getManufacturerIdentification().getManufacturer();
  }
  private static String getRevisionIdentification(final Neul modem) throws TimeoutException, ResponseException, SerialException, InterruptedException{
    return modem.getRevisionIdentification().getRevision();
  }

}
