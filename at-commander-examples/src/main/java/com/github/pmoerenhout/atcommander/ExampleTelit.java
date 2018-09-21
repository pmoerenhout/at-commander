package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_RTSCTS_IN;
import static jssc.SerialPort.FLOWCONTROL_RTSCTS_OUT;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse;
import com.github.pmoerenhout.atcommander.module.telit.TelitModem;
import com.github.pmoerenhout.atcommander.module.telit.commands.types.Band;
import com.github.pmoerenhout.atcommander.module.v250.enums.WirelessNetwork;

public class ExampleTelit {

  private static final Logger LOG = LoggerFactory.getLogger(ExampleTelit.class);

  public static void main(String[] arg) {
    LOG.info("Test Telit modem");

    final TelitModem modem = new TelitModem(
        new JsscSerial("/dev/tty.usbserial-00101414B", 115200, FLOWCONTROL_RTSCTS_IN | FLOWCONTROL_RTSCTS_OUT,
            new UnsolicitedCallback()));
    try {
      modem.init();
      LOG.info("Telit modem via jSSC is initialized");

      modem.setCellularResultCodes(true);
      modem.setNitz(1,1);

      modem.setWirelessNetwork(WirelessNetwork.GSM);
      final Band band = modem.getBand();
      LOG.info("Band GSM:{} UMTS:{}", band.getGsmBand(), band.getUmtsBand());
      LOG.info("ICCID: {}", modem.getIntegratedCircuitCardIdentification());
      LOG.info("IMSI: {}", modem.getInternationalMobileSubscriberIdentity());
      LOG.info("SPN (Service Provider Name): {}", modem.getServiceProviderName());
      modem.setNetworkRegistration(2);
      modem.setGprsNetworkRegistration(2);
      final NetworkRegistrationResponse networkRegistrationResponse = modem.getNetworkRegistration();
      LOG.info("Network Registration: mode:{} state:{} LAC:{} CID:{}",
          networkRegistrationResponse.getMode(),
          networkRegistrationResponse.getRegistrationState(),
          networkRegistrationResponse.getLac(),
          networkRegistrationResponse.getCellId());
      modem.getGprsNetworkRegistration();
      modem.setGprsAttach(true, 60000);
      modem.getGprsNetworkRegistration();
      Thread.sleep(10000);
      modem.close();
      LOG.info("Telit modem via jSSC is closed");
    } catch (final Exception e) {
      LOG.error("The modem had an error", e);
    }
  }

}
