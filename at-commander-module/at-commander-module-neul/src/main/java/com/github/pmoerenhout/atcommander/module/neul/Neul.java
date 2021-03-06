package com.github.pmoerenhout.atcommander.module.neul;


import java.util.ArrayList;
import java.util.List;

import com.github.pmoerenhout.atcommander.AtCommander;
import com.github.pmoerenhout.atcommander.api.InitException;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.basic.Basic;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.module.neul.commands.ManufacturerIdentificationCommand;
import com.github.pmoerenhout.atcommander.module.neul.commands.ManufacturerIdentificationResponse;
import com.github.pmoerenhout.atcommander.module.neul.commands.NeulConfigCommand;
import com.github.pmoerenhout.atcommander.module.neul.commands.NeulConfigResponse;
import com.github.pmoerenhout.atcommander.module.neul.commands.NeulRebootCommand;
import com.github.pmoerenhout.atcommander.module.neul.commands.RevisionIdentificationCommand;
import com.github.pmoerenhout.atcommander.module.neul.commands.RevisionIdentificationResponse;
import com.github.pmoerenhout.atcommander.module.neul.commands.SignalQualityCommand;
import com.github.pmoerenhout.atcommander.module.neul.commands.SignalQualityResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.AnyResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.IdentificationInformationCommand;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Neul extends Basic {

  // http://www.c-dis.net/media/images/upload/Quectel_BC95_AT_Commands_Manual_V1.8.pdf
  // http://support.sodaq.com/sodaq-one/at/

  private static final ArrayList<UnsolicitedPatternClass> UNSOLICITED_PATTERN_CLASS_LIST = new ArrayList<>();

  public Neul(final SerialInterface serial) {
    super(serial);
    UNSOLICITED_PATTERN_CLASS_LIST.forEach(u -> serial.addUnsolicited(u));
  }

  public void init() throws InitException {
    try {
      atCommander = new AtCommander(serial);
      serial.setSolicitedResponseCallback(atCommander);
      atCommander.init();
      isResponsive();
    } catch (final Exception e) {
      log.error("Error init device", e);
      throw new InitException(e);
    }
  }

  public void close() {
    if (atCommander != null) {
      atCommander.close();
    }
  }

  public void reboot() throws SerialException, TimeoutException, ResponseException {
    final NeulRebootCommand command = new NeulRebootCommand(atCommander);
    command.set();
  }

  public void config(final String setting, final String value) throws SerialException, TimeoutException, ResponseException, InterruptedException {
    Thread.sleep(50);
    final NeulConfigCommand command = new NeulConfigCommand(atCommander, setting, value);
    command.set();

  }

  public NeulConfigResponse getConfig() throws SerialException, TimeoutException, ResponseException, InterruptedException {
    Thread.sleep(50);
    final NeulConfigCommand command = new NeulConfigCommand(atCommander);
    return command.read();
  }

  public SignalQualityResponse getSignalQuality() throws SerialException, TimeoutException, ResponseException, InterruptedException {
    Thread.sleep(50);
    final SignalQualityCommand command = new SignalQualityCommand(atCommander);
    return command.set();
  }

  public RevisionIdentificationResponse getRevisionIdentification() throws SerialException, TimeoutException, ResponseException, InterruptedException {
    Thread.sleep(50);
    final RevisionIdentificationCommand command = new RevisionIdentificationCommand(atCommander);
    return command.set();
  }

  public ManufacturerIdentificationResponse getManufacturerIdentification() throws SerialException, TimeoutException, ResponseException, InterruptedException {
    Thread.sleep(50);
    final ManufacturerIdentificationCommand command = new ManufacturerIdentificationCommand(atCommander);
    return command.set();
  }

  public List<String> getIdentificationInformation() throws SerialException, TimeoutException, ResponseException {
    final IdentificationInformationCommand command = new IdentificationInformationCommand(atCommander);
    final AnyResponse response = command.set();
    return response.getLines();
  }

  public List<String> getIdentificationInformation(final int information) throws SerialException, TimeoutException, ResponseException {
    final IdentificationInformationCommand command = new IdentificationInformationCommand(atCommander, information);
    final AnyResponse response = command.set();
    return response.getLines();
  }
}
