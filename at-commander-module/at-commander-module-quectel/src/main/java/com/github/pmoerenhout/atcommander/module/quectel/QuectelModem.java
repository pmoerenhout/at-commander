package com.github.pmoerenhout.atcommander.module.quectel;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.api.InitException;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.module._3gpp.EtsiModem;
import com.github.pmoerenhout.atcommander.module.quectel.commands.ActivatePdpContextCommand;
import com.github.pmoerenhout.atcommander.module.quectel.commands.ConfigureParametersTcpipContextCommand;
import com.github.pmoerenhout.atcommander.module.quectel.commands.DeactivatePdpContextCommand;
import com.github.pmoerenhout.atcommander.module.quectel.commands.ExtendedConfigurationSettingsCommand;
import com.github.pmoerenhout.atcommander.module.quectel.commands.IccidCommand;
import com.github.pmoerenhout.atcommander.module.quectel.commands.IccidResponse;
import com.github.pmoerenhout.atcommander.module.quectel.commands.InitializationStatusCommand;
import com.github.pmoerenhout.atcommander.module.quectel.commands.InitializationStatusResponse;
import com.github.pmoerenhout.atcommander.module.quectel.commands.PingCommand;
import com.github.pmoerenhout.atcommander.module.quectel.commands.SynchronizeNetworkTimeProtocolCommand;
import com.github.pmoerenhout.atcommander.module.quectel.unsolicited.QuectelIndicationUnsolicited;
import com.github.pmoerenhout.atcommander.module.quectel.unsolicited.QuectelPingUnsolicited;
import com.github.pmoerenhout.atcommander.module.quectel.unsolicited.QuectelPsmTimerUnsolicited;
import com.github.pmoerenhout.atcommander.module.quectel.unsolicited.QuectelUsimUnsolicited;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class QuectelModem extends EtsiModem {

  private static final Logger LOG = LoggerFactory.getLogger(QuectelModem.class);

  private static final ArrayList<UnsolicitedPatternClass> UNSOLICITED_PATTERN_CLASS_LIST = new ArrayList<>(Arrays.asList(
//      // new UnsolicitedPatternClass(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, NetworkRegistrationUnsolicited.class),
////      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN_1, GprsNetworkRegistrationResponse.class),
////      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN_2, GprsNetworkRegistrationResponse.class),
//      new UnsolicitedPatternClass(QuerySimStatusUnsolicited.UNSOLICITED_PATTERN, QuerySimStatusUnsolicited.class),
//      new UnsolicitedPatternClass(IndicatorEventUnsolicited.UNSOLICITED_PATTERN, IndicatorEventUnsolicited.class),
//      new UnsolicitedPatternClass(SocketRingUnsolicited.UNSOLICITED_PATTERN, SocketRingUnsolicited.class),
//      new UnsolicitedPatternClass(NitzUnsolicited.UNSOLICITED_PATTERN, NitzUnsolicited.class),
//      new UnsolicitedPatternClass(PacketServiceNetworkTypeUnsolicited.UNSOLICITED_PATTERN, PacketServiceNetworkTypeUnsolicited.class),
//      new UnsolicitedPatternClass(SimPresenceStatusUnsolicited.UNSOLICITED_PATTERN, SimPresenceStatusUnsolicited.class),
//      new UnsolicitedPatternClass(ConnectionFromUnsolicited.UNSOLICITED_PATTERN, ConnectionFromUnsolicited.class),
//      new UnsolicitedPatternClass(GpsNmunUnsolicited.UNSOLICITED_PATTERN, GpsNmunUnsolicited.class),
//      new UnsolicitedPatternClass(HttpRingUnsolicited.UNSOLICITED_PATTERN, HttpRingUnsolicited.class),
//      new UnsolicitedPatternClass(JammedStatusUnsolicited.UNSOLICITED_PATTERN, JammedStatusUnsolicited.class),
//      new UnsolicitedPatternClass(CodecInformationUnsolicited.UNSOLICITED_PATTERN, CodecInformationUnsolicited.class),
//      new UnsolicitedPatternClass(NoCarrierUnsolicited.UNSOLICITED_PATTERN, NoCarrierUnsolicited.class),
//      new UnsolicitedPatternClass(DialingUnsolicited.UNSOLICITED_PATTERN, DialingUnsolicited.class),
//      new UnsolicitedPatternClass(RingingUnsolicited.UNSOLICITED_PATTERN, RingingUnsolicited.class),
//      new UnsolicitedPatternClass(DisconnectedUnsolicited.UNSOLICITED_PATTERN, DisconnectedUnsolicited.class),
//      // new UnsolicitedPatternClass(ReleasedResponse.UNSOLICITED_PATTERN, ReleasedResponse.class)
//      new UnsolicitedPatternClass(SimToolkitNotificationUnsolicited.UNSOLICITED_PATTERN, SimToolkitNotificationUnsolicited.class),
//      new UnsolicitedPatternClass(SmsAtRunUnsolicited.UNSOLICITED_PATTERN, SmsAtRunUnsolicited.class),
//      new UnsolicitedPatternClass(SmsOverflowUnsolicited.UNSOLICITED_PATTERN, SmsOverflowUnsolicited.class)
      new UnsolicitedPatternClass(QuectelIndicationUnsolicited.UNSOLICITED_PATTERN, QuectelIndicationUnsolicited.class),
      new UnsolicitedPatternClass(QuectelUsimUnsolicited.UNSOLICITED_PATTERN, QuectelUsimUnsolicited.class),
      new UnsolicitedPatternClass(QuectelPsmTimerUnsolicited.UNSOLICITED_PATTERN, QuectelPsmTimerUnsolicited.class),
      new UnsolicitedPatternClass(QuectelPingUnsolicited.UNSOLICITED_PATTERN, QuectelPingUnsolicited.class)
  ));

  private static final Pattern REVISION_PATTERN = Pattern.compile("(\\d*).(\\d*).(\\d*)(-.*|)$");
  public AccessTechnology accessTechnology = null;
//  private Integer interfaceStyle = null;
//  private Integer smsMode = null;

  public QuectelModem(final SerialInterface serial) {
    super(serial);
    //atCommander.addFinalResponseFactory(new TelitFinalFactory());
    UNSOLICITED_PATTERN_CLASS_LIST.forEach(u -> serial.addUnsolicited(u));
  }

  public void init() throws InitException, SerialException, TimeoutException, ResponseException {
    super.init();
    final String revisionIdentification = this.getRevisionIdentification();
//    firmware = parseRevision(revisionIdentification);
//    LOG.info("Revision {} => Firmware {}.{}.{}", revisionIdentification, firmware.getMajor(), firmware.getMinor(), firmware.getFeature());
    LOG.info("Revision {}", revisionIdentification);
  }

  public String getIntegratedCircuitCardIdentification() throws SerialException, TimeoutException, ResponseException {
    final IccidCommand command = new IccidCommand(atCommander);
    final IccidResponse response = command.set();
    return response.getIccid();
  }

  public int getInitializationState() throws SerialException, TimeoutException, ResponseException {
    final InitializationStatusCommand command = new InitializationStatusCommand(atCommander);
    final InitializationStatusResponse response = command.set();
    LOG.info("response {}", response);
    return response.getStatus();
  }

  public void setExtendedConfiguration(final String parameter, final Object... values)
      throws SerialException, TimeoutException, ResponseException {
    final ExtendedConfigurationSettingsCommand command = new ExtendedConfigurationSettingsCommand(atCommander, parameter, values);
    command.set();
  }

  public void configureParametersTcpipContext(final int contextId, final int contextType,
                                              final String apn) throws SerialException, TimeoutException, ResponseException {
    final ConfigureParametersTcpipContextCommand command = new ConfigureParametersTcpipContextCommand(atCommander, contextId, contextType, apn);
    command.set();
  }

  public void activatePdpContext(final int contextId) throws SerialException, TimeoutException, ResponseException {
    final ActivatePdpContextCommand command = new ActivatePdpContextCommand(atCommander, contextId);
    command.set();
  }

  public void deactivatePdpContext(final int contextId) throws SerialException, TimeoutException, ResponseException {
    final DeactivatePdpContextCommand command = new DeactivatePdpContextCommand(atCommander, contextId);
    command.set();
  }

  public void ping(final int contextId, final String host) throws SerialException, TimeoutException, ResponseException {
    final PingCommand command = new PingCommand(atCommander, contextId, host);
    command.set();
  }

  public void synchronizeNtp(final int contextId, final String address) throws SerialException, TimeoutException, ResponseException {
    final SynchronizeNetworkTimeProtocolCommand command = new SynchronizeNetworkTimeProtocolCommand(atCommander, contextId, address);
    command.set();
  }

  public void synchronizeNtp(final int contextId, final String address, final int port) throws SerialException, TimeoutException, ResponseException {
    final SynchronizeNetworkTimeProtocolCommand command = new SynchronizeNetworkTimeProtocolCommand(atCommander, contextId, address, port);
    command.set();
  }

  public void synchronizeNtp(final int contextId, final String address, final int port,
                             final boolean autoSetTime) throws SerialException, TimeoutException, ResponseException {
    final SynchronizeNetworkTimeProtocolCommand command = new SynchronizeNetworkTimeProtocolCommand(atCommander, contextId, address, port, autoSetTime);
    command.set();
  }

}
