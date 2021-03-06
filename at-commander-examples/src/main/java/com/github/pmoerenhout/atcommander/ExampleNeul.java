package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_XONXOFF_IN;
import static jssc.SerialPort.FLOWCONTROL_XONXOFF_OUT;

import java.util.List;

import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import com.github.pmoerenhout.atcommander.module.neul.Neul;
import com.github.pmoerenhout.atcommander.module.neul.commands.SignalQualityResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleNeul {

  public static void main(String[] arg) {

    final Neul modem = new Neul(
        // new JsscSerial("/dev/tty.usbserial-A1056QHL", 9600, FLOWCONTROL_XONXOFF_IN | FLOWCONTROL_XONXOFF_OUT, new UnsolicitedCallback())
        new JsscSerial("/dev/tty.usbmodem1411101", 9600, FLOWCONTROL_XONXOFF_IN | FLOWCONTROL_XONXOFF_OUT, new UnsolicitedCallback())
    );

    try {
      modem.init();
      log.info("Is the modem responsive? {}", modem.isResponsive());
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

      log.info("Identification: {}", getIdentificationInformation(modem, 0));
      log.info("Identification IMEI: {}", getIdentificationInformation(modem, 5));
//      IntStream.range(0,10).forEachOrdered(information -> {
//        try {
//          LOG.info("Identification {}: {}", information, getIdentificationInformation(modem, information));
//        } catch (Exception e){
//          LOG.error("Identification", e);
//        }
//      });
      log.info("Manufacturer: {}", getManufacturer(modem));
      log.info("Module revision: {}", getRevisionIdentification(modem));
      Thread.sleep(1000);

      modem.getConfig().getItems().forEach(l -> log.info("Configuration: {} is {}", l.getLeft(), l.getRight()));
      final SignalQualityResponse signalQualityResponse = modem.getSignalQuality();
      log.info("Received signal strength indicator is {} and bit error rate is {}", signalQualityResponse.getRssi(), signalQualityResponse.getBer());
      modem.config("AUTOCONNECT", "FALSE");
      modem.config("CR_0354_0338_SCRAMBLING", "FALSE");
      //modem.config("CR_0859_SI_AVOID", "FALSE");
      // T-Mobile forum
      modem.config("CR_0354_0338_SCRAMBLING", "TRUE");
      modem.config("CR_0859_SI_AVOID", "TRUE");
      modem.getConfig().getItems().forEach(l -> log.info("Configuration: {} is {}", l.getLeft(), l.getRight()));
      modem.close();
    } catch (final Exception e) {
      log.error("The modem had an error", e);
    }

  }

  private static List<String> getIdentificationInformation(final Neul modem) throws TimeoutException, ResponseException, SerialException, InterruptedException {
    return modem.getIdentificationInformation();
  }

  private static List<String> getIdentificationInformation(final Neul modem,
                                                           final int information) throws TimeoutException, ResponseException, SerialException, InterruptedException {
    return modem.getIdentificationInformation(information);
  }


  private static String getManufacturer(final Neul modem) throws TimeoutException, ResponseException, SerialException, InterruptedException {
    return modem.getManufacturerIdentification().getManufacturer();
  }

  private static String getRevisionIdentification(final Neul modem) throws TimeoutException, ResponseException, SerialException, InterruptedException {
    return modem.getRevisionIdentification().getRevision();
  }

}
