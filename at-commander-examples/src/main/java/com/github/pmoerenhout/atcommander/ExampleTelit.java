package com.github.pmoerenhout.atcommander;

import static jssc.SerialPort.FLOWCONTROL_RTSCTS_IN;
import static jssc.SerialPort.FLOWCONTROL_RTSCTS_OUT;

import com.github.pmoerenhout.atcommander.jssc.JsscSerial;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse;
import com.github.pmoerenhout.atcommander.module.telit.TelitModem;
import com.github.pmoerenhout.atcommander.module.telit.commands.types.Band;
import com.github.pmoerenhout.atcommander.module.v250.enums.WirelessNetwork;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleTelit {

  public static void main(String[] arg) {
    log.info("Test Telit modem");

    final TelitModem modem = new TelitModem(
        new JsscSerial("/dev/tty.usbserial-00101414B", 115200, FLOWCONTROL_RTSCTS_IN | FLOWCONTROL_RTSCTS_OUT,
            new UnsolicitedCallback()));
    try {
      modem.init();
      log.info("Telit modem via jSSC is initialized");

      log.info("Phone activity status: {}", modem.getPhoneActivityStatus());

      modem.setCellularResultCodes(true);
      modem.setNitz(1, 1);

      modem.setWirelessNetwork(WirelessNetwork.GSM);
      final Band band = modem.getBand();
      log.info("Band GSM:{} UMTS:{}", band.getGsmBand(), band.getUmtsBand());
      log.info("ICCID: {}", modem.getIntegratedCircuitCardIdentification());
      log.info("IMSI: {}", modem.getInternationalMobileSubscriberIdentity());
      log.info("SPN (Service Provider Name): {}", modem.getServiceProviderName());
      modem.setNetworkRegistration(2);
      modem.setGprsNetworkRegistration(2);
      final NetworkRegistrationResponse networkRegistrationResponse = modem.getNetworkRegistration();
      log.info("Network Registration: mode:{} state:{} LAC:{} CID:{}",
          networkRegistrationResponse.getMode(),
          networkRegistrationResponse.getRegistrationState(),
          networkRegistrationResponse.getLac(),
          networkRegistrationResponse.getCellId());
      modem.getGprsNetworkRegistration();
      modem.setGprsAttach(true, 60000);
      modem.getGprsNetworkRegistration();
      Thread.sleep(10000);
      modem.close();
      log.info("Telit modem via jSSC is closed");
    } catch (final Exception e) {
      log.error("The modem had an error", e);
    }
  }

}
