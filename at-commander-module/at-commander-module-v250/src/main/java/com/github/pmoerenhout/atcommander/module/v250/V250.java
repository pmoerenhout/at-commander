package com.github.pmoerenhout.atcommander.module.v250;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.InitException;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.basic.Basic;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.SimpleCommand;
import com.github.pmoerenhout.atcommander.basic.commands.SimpleResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module.v250.commands.AnswerCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.AnyResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.Circuit108Command;
import com.github.pmoerenhout.atcommander.module.v250.commands.Circuit109Command;
import com.github.pmoerenhout.atcommander.module.v250.commands.DialCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.DialResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.ExtendedResultCodesCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.FactoryConfigurationCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.HangupCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.IdentificationInformationCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.QuietResultCodesCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.RingResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.SParameterCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.SelectModeCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.SerialNumberCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.SerialNumberResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.SoftResetCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.StoreCurrentConfigurationCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.VerboseCommand;
import com.github.pmoerenhout.atcommander.module.v250.exceptions.UnknownResponseException;

public class V250 extends Basic {

  // A.K.A. ITU-T V.250
  // http://www.itu.int/rec/T-REC-V.25ter-199508-S
  private static final Logger LOG = LoggerFactory.getLogger(V250.class);

  private static final ArrayList<UnsolicitedPatternClass> UNSOLICITED_PATTERN_CLASS_LIST = new ArrayList<>(Arrays.asList(
      // new UnsolicitedPatternClass(NoCarrierResponse.PATTERN, NoCarrierResponse.class),
      new UnsolicitedPatternClass(RingResponse.PATTERN, RingResponse.class)
  ));

  public V250(final SerialInterface serial) {
    super(serial);
    UNSOLICITED_PATTERN_CLASS_LIST.forEach(u -> serial.addUnsolicited(u));
  }

  public void init() throws InitException, SerialException, TimeoutException, ResponseException {
    super.init();
    atCommander.addFinalResponseFactory(new V250FinalFactory());
    LOG.info("Disable local echo");
    setEcho(false);
  }

  public void close() {
    if (atCommander != null) {
      atCommander.close();
    }
  }

  public Command<BaseResponse> getSimpleCommand(final String command) {
    return new SimpleCommand(atCommander, command);
  }

  public boolean isResponsive() throws SerialException {
    try {
      final SimpleCommand command = new SimpleCommand(atCommander, "AT");
      command.setTimeout(10000);
      command.set();
      return true;
    } catch (final TimeoutException e) {
      LOG.warn("The AT interface is not responsive: {}", e.getMessage());
    } catch (final ResponseException e) {
      LOG.warn("Other error on AT interface: {}", e.getMessage());
    }
    return false;
  }

  public void setEcho(final boolean echo) throws SerialException, TimeoutException {
    try {
      final SimpleCommand command = new SimpleCommand(atCommander, echo ? "ATE1" : "ATE0");
      command.set();
    } catch (final ResponseException e) {
      LOG.warn("Could not set the echo to {}: {}", echo, e.getMessage());
    }
  }

  public String getSerialNumber() throws SerialException, TimeoutException, ResponseException {
    final SerialNumberCommand command = new SerialNumberCommand(atCommander);
    final SerialNumberResponse resp = command.set();
    return resp.getSerialNumber();
  }

  public void factoryConfiguration() throws SerialException, TimeoutException, ResponseException {
    final FactoryConfigurationCommand command = new FactoryConfigurationCommand(atCommander);
    command.set();
  }

  public void factoryConfiguration(final int profile) throws SerialException, TimeoutException, ResponseException {
    final FactoryConfigurationCommand command = new FactoryConfigurationCommand(atCommander, profile);
    command.set();
  }

  public void storeConfiguration() throws SerialException, TimeoutException, ResponseException {
    final StoreCurrentConfigurationCommand command = new StoreCurrentConfigurationCommand(atCommander);
    command.set();
  }

  public void storeConfiguration(final int profile) throws SerialException, TimeoutException, ResponseException {
    final StoreCurrentConfigurationCommand command = new StoreCurrentConfigurationCommand(atCommander, profile);
    command.set();
  }

  public void softReset() throws SerialException, TimeoutException, ResponseException {
    final SoftResetCommand command = new SoftResetCommand(atCommander);
    command.set();
    LOG.trace("Soft reset done, wait 5000ms for modem to come alive");
    sleep(5000);
  }

  public void softReset(final int profile) throws SerialException, TimeoutException, ResponseException {
    final SoftResetCommand command = new SoftResetCommand(atCommander, profile);
    command.set();
    LOG.trace("Soft reset with profile {} done, wait 5000ms for modem to come alive", profile);
    sleep(5000);
  }

  public void setVerbose(final int format) throws SerialException, TimeoutException, ResponseException {
    final VerboseCommand command = new VerboseCommand(atCommander, format);
    command.set();
  }

  public void setQuietResultCodes(final int mode) throws SerialException, TimeoutException, ResponseException {
    final QuietResultCodesCommand command = new QuietResultCodesCommand(atCommander, mode);
    command.set();
  }

  public void setExtendedResultCodes(final int value) throws SerialException, TimeoutException, ResponseException {
    final ExtendedResultCodesCommand command = new ExtendedResultCodesCommand(atCommander, value);
    command.set();
  }

  public void setCircuit109(final int value) throws SerialException, TimeoutException, ResponseException {
    final Circuit109Command command = new Circuit109Command(atCommander, value);
    command.set();
  }

  public void setCircuit108(final int value) throws SerialException, TimeoutException, ResponseException {
    final Circuit108Command command = new Circuit108Command(atCommander, value);
    command.set();
  }

  public List<String> getIdentificationInformation(final int information) throws SerialException, TimeoutException, ResponseException {
    final IdentificationInformationCommand command = new IdentificationInformationCommand(atCommander, information);
    final AnyResponse response = command.set();
    return response.getLines();
  }

  public DialResponse dial(final String number) throws SerialException, TimeoutException, ResponseException {
    final DialCommand command = new DialCommand(atCommander, number);
    return command.set();
  }

  public DialResponse dial(final String number, final long timeout) throws SerialException, TimeoutException, ResponseException {
    final DialCommand command = new DialCommand(atCommander, number);
    command.setTimeout(timeout);
    return command.set();
  }

  public void answer() throws SerialException, TimeoutException, ResponseException {
    final AnswerCommand command = new AnswerCommand(atCommander);
    command.set();
  }

  public void escapeAndHangup(final byte escapeCharacter, final int delay) throws SerialException {
    try {
      escape(escapeCharacter, delay);
      hangup(10000);
    } catch (final TimeoutException e) {
      LOG.debug("Could not hangup: {}", e.getMessage());
    }
  }

  public void hangup(final long timeout) throws SerialException, TimeoutException {
    try {
      final HangupCommand command = new HangupCommand(atCommander);
      command.setTimeout(timeout);
      command.set();
    } catch (final ResponseException e) {
      LOG.warn("Could not hangup: {}", e.getMessage());
    }
  }

  public void escape(final byte escapeCharacter, final long delay) throws SerialException {
    // time of S12 in 1/50 of a second;
    // final int delay = 50 * 20;
    LOG.debug("Escape sequence with delay {}ms", delay);
    final byte[] esc = new byte[]{ escapeCharacter };
    sleep(delay);
    for (int i = 0; i < 3; i++) {
      LOG.debug("Send escape [{}] {}", (i + 1), Util.onlyPrintable(esc));
      atCommander.write(esc);
    }
    sleep(delay);
  }

  protected void sleep(final long millis) {
    try {
      Thread.sleep(millis);
    } catch (final InterruptedException e) {
      LOG.debug("Sleep was interrupted: {}", e.getMessage());
      throw new RuntimeException("Sleep was interrupted");
    }
  }

  public void setSelectMode(final int mode) throws SerialException, TimeoutException, ResponseException {
    final SelectModeCommand command = new SelectModeCommand(atCommander, mode);
    command.set();
    return;
  }

  public Byte getSParameter(final int parameter) throws SerialException, TimeoutException, ResponseException {
    try {
      final SParameterCommand command = new SParameterCommand(atCommander, parameter);
      final SimpleResponse response = command.set();
      final byte value = Byte.parseByte(response.getValue());
      LOG.debug("S parameter value: {} -> {}", response.getValue(), value);
      return value;
    } catch (final NumberFormatException e) {
      LOG.warn("Invalid format of S parameter: {}", parameter, e.getMessage());
      throw new UnknownResponseException(e);
    } catch (final ResponseException e) {
      LOG.warn("Could not read the S{} parameter: {}", parameter, e.getMessage());
      throw e;
    }
  }

}
