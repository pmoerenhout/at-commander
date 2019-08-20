package com.github.pmoerenhout.atcommander.module.telit;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.Command;
import com.github.pmoerenhout.atcommander.api.InitException;
import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.SimpleCommand;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.EtsiModem;
import com.github.pmoerenhout.atcommander.module._3gpp.SimStatus;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsActStatusResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsActivateCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.MobileEquipmentErrorModeCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SimDetectionModeCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.sms.SmsPdu;
import com.github.pmoerenhout.atcommander.module._3gpp.types.GprsAct;
import com.github.pmoerenhout.atcommander.module.telit.commands.AuthenticationPasswordCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.AuthenticationUserIdCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.BandCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.BandResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.EasyGprsActivationAuthenticationCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.EasyGprsActivationCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.EasyGprsActivationConfigurationCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.EasyGprsActivationReadResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.EasyGprsActivationResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.ExtendedNumericErrorReportCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.ExtendedNumericErrorReportForNetworkRejectCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.ExtendedNumericErrorReportForNetworkRejectResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.ExtendedNumericErrorReportResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.GeneralPurposeInputOutputCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.GprsAuthenticationTypeCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.GprsCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.GprsDataVolumeCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.GprsDataVolumeResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.GprsResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.GprsStatusResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.GpsNmeaUnsolicitedCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.GpsNmunUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.commands.GpsPowerCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.GpsResetCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.GpsRestoreCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.HttpConfigurationCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.HttpQueryCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.HttpReceiveCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.IccidCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.IccidResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.IndicatorControlCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.IndicatorControlResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.MobileEquipmentEventReportingCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.NetworkDnsCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.NetworkDnsResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.NitzCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.PacketServiceNetworkTypeCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.PacketServiceNetworkTypeUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.commands.PingCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.PingResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.QuerySimStatusCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.QuerySimStatusResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.ReadNetworkStatusCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.ReadNetworkStatusResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.RebootCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SelectInterfaceStyleCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SendMessageCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SendMessageResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.ServInfoCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.ServInfoResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.ServiceProviderNameCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.ServiceProviderNameResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.SimPresenceStatusCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SimPresenceStatusResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.SmsAtRunCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SmsAtRunConfigurationCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SmsAtRunConfigurationResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.SmsAtRunWhiteListCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SmsCommandsOperationModeCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketAcceptCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketConfigCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketConfigurationExtended2Command;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketConfigurationExtended3Command;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketConfigurationExtendedCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketDialCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketInformationCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketInformationResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketLastClosureCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketLastClosureResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketListenCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketListenUdpCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketReceiveCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketReceiveResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketSendCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketSendExtendedCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketSendUdpCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketShutdownCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketStatusCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketStatusResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketTypeCommand;
import com.github.pmoerenhout.atcommander.module.telit.commands.SocketTypeResponse;
import com.github.pmoerenhout.atcommander.module.telit.commands.types.Band;
import com.github.pmoerenhout.atcommander.module.telit.enums.ClosureType;
import com.github.pmoerenhout.atcommander.module.telit.enums.ConnectionMode;
import com.github.pmoerenhout.atcommander.module.telit.enums.GprsDataVolumeMode;
import com.github.pmoerenhout.atcommander.module.telit.enums.GpsResetType;
import com.github.pmoerenhout.atcommander.module.telit.enums.GsmBand;
import com.github.pmoerenhout.atcommander.module.telit.enums.SringMode;
import com.github.pmoerenhout.atcommander.module.telit.enums.TransmissionProtocol;
import com.github.pmoerenhout.atcommander.module.telit.types.ContextStatus;
import com.github.pmoerenhout.atcommander.module.telit.types.GprsDataVolume;
import com.github.pmoerenhout.atcommander.module.telit.types.NetworkDns;
import com.github.pmoerenhout.atcommander.module.telit.types.Ping;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketInformation;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketLastClosure;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketStatus;
import com.github.pmoerenhout.atcommander.module.telit.types.SocketType;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.CodecInformationUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.DialingUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.DisconnectedUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.HttpRingUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.IndicatorEventUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.JammedStatusUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.NitzUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.NoCarrierUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.QuerySimStatusUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.RingingUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.SimPresenceStatusUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.SimToolkitNotificationUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.SmsAtRunUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.SmsOverflowUnsolicited;
import com.github.pmoerenhout.atcommander.module.telit.unsolicited.SocketRingUnsolicited;
import com.github.pmoerenhout.atcommander.module.v250.commands.AnyResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.HangupCommand;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;
import com.github.pmoerenhout.atcommander.module.v250.enums.Authentication;
import com.github.pmoerenhout.atcommander.module.v250.enums.DataMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.UmtsBand;
import com.github.pmoerenhout.atcommander.module.v250.unsolicited.ConnectionFromUnsolicited;

public class TelitModem extends EtsiModem {

  private static final Logger LOG = LoggerFactory.getLogger(TelitModem.class);

  private static final ArrayList<UnsolicitedPatternClass> UNSOLICITED_PATTERN_CLASS_LIST = new ArrayList<>(Arrays.asList(
      // new UnsolicitedPatternClass(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, NetworkRegistrationUnsolicited.class),
//      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN_1, GprsNetworkRegistrationResponse.class),
//      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN_2, GprsNetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(QuerySimStatusUnsolicited.UNSOLICITED_PATTERN, QuerySimStatusUnsolicited.class),
      new UnsolicitedPatternClass(IndicatorEventUnsolicited.UNSOLICITED_PATTERN, IndicatorEventUnsolicited.class),
      new UnsolicitedPatternClass(SocketRingUnsolicited.UNSOLICITED_PATTERN, SocketRingUnsolicited.class),
      new UnsolicitedPatternClass(NitzUnsolicited.UNSOLICITED_PATTERN, NitzUnsolicited.class),
      new UnsolicitedPatternClass(PacketServiceNetworkTypeUnsolicited.UNSOLICITED_PATTERN, PacketServiceNetworkTypeUnsolicited.class),
      new UnsolicitedPatternClass(SimPresenceStatusUnsolicited.UNSOLICITED_PATTERN, SimPresenceStatusUnsolicited.class),
      new UnsolicitedPatternClass(ConnectionFromUnsolicited.UNSOLICITED_PATTERN, ConnectionFromUnsolicited.class),
      new UnsolicitedPatternClass(GpsNmunUnsolicited.UNSOLICITED_PATTERN, GpsNmunUnsolicited.class),
      new UnsolicitedPatternClass(HttpRingUnsolicited.UNSOLICITED_PATTERN, HttpRingUnsolicited.class),
      new UnsolicitedPatternClass(JammedStatusUnsolicited.UNSOLICITED_PATTERN, JammedStatusUnsolicited.class),
      new UnsolicitedPatternClass(CodecInformationUnsolicited.UNSOLICITED_PATTERN, CodecInformationUnsolicited.class),
      new UnsolicitedPatternClass(NoCarrierUnsolicited.UNSOLICITED_PATTERN, NoCarrierUnsolicited.class),
      new UnsolicitedPatternClass(DialingUnsolicited.UNSOLICITED_PATTERN, DialingUnsolicited.class),
      new UnsolicitedPatternClass(RingingUnsolicited.UNSOLICITED_PATTERN, RingingUnsolicited.class),
      new UnsolicitedPatternClass(DisconnectedUnsolicited.UNSOLICITED_PATTERN, DisconnectedUnsolicited.class),
      // new UnsolicitedPatternClass(ReleasedResponse.UNSOLICITED_PATTERN, ReleasedResponse.class)
      new UnsolicitedPatternClass(SimToolkitNotificationUnsolicited.UNSOLICITED_PATTERN, SimToolkitNotificationUnsolicited.class),
      new UnsolicitedPatternClass(SmsAtRunUnsolicited.UNSOLICITED_PATTERN, SmsAtRunUnsolicited.class),
      new UnsolicitedPatternClass(SmsOverflowUnsolicited.UNSOLICITED_PATTERN, SmsOverflowUnsolicited.class)
  ));

  private static final Pattern REVISION_PATTERN = Pattern.compile("(\\d*).(\\d*).(\\d*)(-.*|)$");
  public AccessTechnology accessTechnology = null;
  private Firmware firmware;
  private Integer interfaceStyle = null;
  private Integer smsMode = null;

  public TelitModem(final SerialInterface serial) {
    super(serial);
    atCommander.addFinalResponseFactory(new TelitFinalFactory());
    UNSOLICITED_PATTERN_CLASS_LIST.forEach(u -> serial.addUnsolicited(u));
  }

  public void init() throws InitException, SerialException, TimeoutException, ResponseException {
    super.init();
    final String revisionIdentification = this.getRevisionIdentification();
    firmware = parseRevision(revisionIdentification);
    LOG.info("Revision {} => Firmware {}.{}.{}", revisionIdentification, firmware.getMajor(), firmware.getMinor(), firmware.getFeature());
  }

  public Firmware parseRevision(final String revision) {
    final Matcher m = REVISION_PATTERN.matcher(revision);
    if (m.find()) {
      return new Firmware(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)));
    }
    throw new ParseException("Could not parse Telit revision identification: " + revision);
  }

  @Override
  public void hangup(final long timeout) throws SerialException, TimeoutException {
    try {
      final HangupCommand command = new HangupCommand(atCommander);
      command.setTimeout(timeout);
      command.set();
    } catch (final ResponseException e) {
      LOG.warn("Could not hangup: {}", e.getMessage());
    }
  }

  public Integer getExtendedNumericErrorReportForNetworkReject() throws SerialException, TimeoutException, ResponseException {
    final ExtendedNumericErrorReportForNetworkRejectCommand command = new ExtendedNumericErrorReportForNetworkRejectCommand(atCommander);
    final ExtendedNumericErrorReportForNetworkRejectResponse response = command.set();
    return response.getCode();
  }

  public Command<BaseResponse> getPacketServiceNetworkTypeCommand(final int mode) {
    return new PacketServiceNetworkTypeCommand(atCommander, mode);
  }

  public String getIntegratedCircuitCardIdentification() throws SerialException, TimeoutException, ResponseException {
    final IccidCommand command = new IccidCommand(atCommander);
    final IccidResponse response = command.set();
    return response.getIccid();
  }

  public int getInterfaceStyle() throws SerialException, TimeoutException, ResponseException {
    final SelectInterfaceStyleCommand command = new SelectInterfaceStyleCommand(atCommander);
    this.interfaceStyle = command.read().getInterfaceStyle();
    return this.interfaceStyle;
  }

  public void setInterfaceStyle(final int value) throws SerialException, TimeoutException, ResponseException {
    final SelectInterfaceStyleCommand command = new SelectInterfaceStyleCommand(atCommander, value);
    command.set();
    this.interfaceStyle = value;
  }

  public int getSmsCommandsOperationModeCommand() throws SerialException, TimeoutException, ResponseException {
    final SmsCommandsOperationModeCommand command = new SmsCommandsOperationModeCommand(atCommander);
    this.smsMode = command.read().getMode();
    return this.smsMode;
  }

  public void setSmsCommandsOperationModeCommand(final int value) throws SerialException, TimeoutException, ResponseException {
    final SmsCommandsOperationModeCommand command = new SmsCommandsOperationModeCommand(atCommander, value);
    command.set();
    this.smsMode = value;
  }

  @Override
  public NetworkRegistrationResponse getNetworkRegistration() throws SerialException, TimeoutException, ResponseException {
    final NetworkRegistrationResponse response = super.getNetworkRegistration();
    if (response.getAccessTechnology() != null) {
      // access technology is optional in response
      accessTechnology = response.getAccessTechnology();
    }
    return response;
  }

  @Override
  public NetworkRegistrationResponse getNetworkRegistration(final long timeout) throws SerialException, TimeoutException, ResponseException {
    final NetworkRegistrationResponse response = super.getNetworkRegistration(timeout);
    if (response.getAccessTechnology() != null) {
      // access technology is optional in response
      accessTechnology = response.getAccessTechnology();
    }
    return response;
  }

//  public void setNetworkRegistration(final int mode) throws SerialException, TimeoutException, ResponseException {
//    final NetworkRegistrationCommand command = new NetworkRegistrationCommand(atCommander, mode);
//    command.set();
//  }

//  @Override
//  public void getGprsNetworkRegistration() throws SerialException, TimeoutException, ResponseException {
//    final GprsNetworkRegistrationCommand command = new GprsNetworkRegistrationCommand(atCommander);
//    command.read();
//  }
//
//  public void setGprsNetworkRegistration(final int mode) throws SerialException, TimeoutException, ResponseException {
//    final GprsNetworkRegistrationCommand command = new GprsNetworkRegistrationCommand(atCommander, mode);
//    command.set();
//  }

  public void reboot() throws SerialException, TimeoutException, ResponseException {
    final RebootCommand command = new RebootCommand(atCommander);
    command.set();
//      close();
//      LOG.info("Reboot done, wait some time for modem to come alive");
//      currentSimSlot = null;
//      imsi = null;
//      iccid = null;
//      ModemUtil.sleep(10000);
  }

  public void setMobileEquipmentErrorMode(final int mode) throws SerialException, TimeoutException, ResponseException {
    final MobileEquipmentErrorModeCommand command = new MobileEquipmentErrorModeCommand(atCommander, mode);
    command.set();
  }

  public Integer getExtendedNumericErrorReport() throws SerialException, TimeoutException, ResponseException {
    final ExtendedNumericErrorReportCommand command = new ExtendedNumericErrorReportCommand(atCommander);
    final ExtendedNumericErrorReportResponse response = command.set();
    return response.getCode();
  }

  public SimStatus getQuerySimStatus() throws SerialException, TimeoutException, ResponseException {
    final QuerySimStatusCommand command = new QuerySimStatusCommand(atCommander);
    final QuerySimStatusResponse response = command.read();
    return response.getStatus();
  }

  public void setQuerySimStatus(final int mode) throws SerialException, TimeoutException, ResponseException {
    final QuerySimStatusCommand command = new QuerySimStatusCommand(atCommander, mode);
    command.set();
  }

  public void setNitz(final int value, final int mode) throws SerialException, TimeoutException, ResponseException {
    final NitzCommand command = new NitzCommand(atCommander, value, mode);
    command.set();
  }

  public String getIdentificationManufacturer() throws SerialException, TimeoutException, ResponseException {
    return getIdentificationInformation(3).get(0);
  }

  public String getIdentificationProductName() throws SerialException, TimeoutException, ResponseException {
    return getIdentificationInformation(4).get(0);
  }

  public String getIdentificationDobVersion() throws SerialException, TimeoutException, ResponseException {
    return getIdentificationInformation(5).get(0);
  }

  public void setIndicatorControl(final boolean batteryChargeLevelState, final boolean signalQualityState, final boolean serviceAvailabilityState,
                                  final boolean sounderActivityState, final boolean messageReceivedState, final boolean callInProgressState,
                                  final boolean roamingState, final boolean smsFullState,
                                  final boolean receivedSignalStrengthState)
      throws SerialException, TimeoutException, ResponseException {
    final IndicatorControlCommand command = new IndicatorControlCommand(atCommander, batteryChargeLevelState, signalQualityState, serviceAvailabilityState,
        sounderActivityState, messageReceivedState, callInProgressState, roamingState, smsFullState, receivedSignalStrengthState);
    command.set();
  }

  public IndicatorControlResponse getIndicatorControl() throws SerialException, TimeoutException, ResponseException {
    final IndicatorControlCommand command = new IndicatorControlCommand(atCommander);
    return command.read();
  }

  public void setMobileEquipmentEventReporting(final int mode, final int keypadEventReporting, final int displayEventReporting,
                                               final int indicatorEventReporting, final int bufferClearing)
      throws SerialException, TimeoutException, ResponseException {
    final MobileEquipmentEventReportingCommand command = new MobileEquipmentEventReportingCommand(atCommander, mode, keypadEventReporting,
        displayEventReporting, indicatorEventReporting, bufferClearing);
    command.set();
  }

  public Band getBand() throws SerialException, TimeoutException, ResponseException {
    final BandCommand command = new BandCommand(atCommander);
    final BandResponse response = command.read();
    final GsmBand gsmBand = response.getBand();
    final UmtsBand umtsBand = response.getUmtsBand();
    if (umtsBand != null) {
      LOG.info("GSM band:{} UMTS band:{}", gsmBand, umtsBand);
    } else {
      LOG.info("GSM band:{}", gsmBand);
    }
    return new Band(gsmBand, umtsBand);
  }

  public ServInfoResponse getServingCellInformation() throws SerialException, TimeoutException, ResponseException {
    LOG.debug("accessTechnology: {}", accessTechnology);
    final ServInfoCommand command = new ServInfoCommand(atCommander, accessTechnology);
    return command.set();
  }

  public ReadNetworkStatusResponse getNetworkStatus() throws SerialException, TimeoutException, ResponseException {
    final ReadNetworkStatusCommand command = new ReadNetworkStatusCommand(atCommander);
    final ReadNetworkStatusResponse response = command.set();
    LOG.info("Network status: {}", response);
    final Integer rssi = response.getRssi();
    final Integer txPower = response.getTxPower();
    final Integer mobilityManagementState = response.getMobilityManagementState();
    final Integer radioResourceState = response.getRadioResourceState();
    LOG.info("RSSI:{} TX:{} MM:{} RR:{}", rssi, txPower, mobilityManagementState, radioResourceState);
    return response;
  }

  public void setSimPresenceStatus(final boolean unsolicitedNotification) throws SerialException, TimeoutException, ResponseException {
    final SimPresenceStatusCommand command = new SimPresenceStatusCommand(atCommander, unsolicitedNotification);
    command.set();
  }

  public SimPresenceStatusResponse readSimPresenceStatus() throws SerialException, TimeoutException, ResponseException {
    final SimPresenceStatusCommand command = new SimPresenceStatusCommand(atCommander);
    final SimPresenceStatusResponse response = command.read();
    //LOG.info("SIMPR: ", response.getValue());
    return response;
  }

  public String getServiceProviderName() throws SerialException, TimeoutException, ResponseException {
    final ServiceProviderNameCommand command = new ServiceProviderNameCommand(atCommander);
    final ServiceProviderNameResponse response = command.set();
    return response.getServiceProviderName();
  }

  public void setSimDetectionMode(final int mode) throws SerialException, TimeoutException, ResponseException {
    final SimDetectionModeCommand command = new SimDetectionModeCommand(atCommander, mode);
    command.set();
  }

  public void setSmsAtRun(final int mode) throws SerialException, TimeoutException, ResponseException {
    final SmsAtRunCommand command = new SmsAtRunCommand(atCommander, mode);
    command.set();
  }

  public void setSmsAtRunConfiguration(final int instance, final int urcMode) throws SerialException, TimeoutException, ResponseException {
    final SmsAtRunConfigurationCommand command = new SmsAtRunConfigurationCommand(atCommander, instance, urcMode);
    command.set();
  }

  public void setSmsAtRunConfiguration(final int instance, final int urcMode, final int timeout) throws SerialException, TimeoutException, ResponseException {
    final SmsAtRunConfigurationCommand command = new SmsAtRunConfigurationCommand(atCommander, instance, urcMode, timeout);
    command.set();
  }

  public SmsAtRunConfigurationResponse getSmsAtRunConfiguration() throws SerialException, TimeoutException, ResponseException {
    final SmsAtRunConfigurationCommand command = new SmsAtRunConfigurationCommand(atCommander);
    return command.read();
  }

  public void setSmsAtRunWhiteList(final int action, final int index, final int entryType,
                                   final String phonenumberOrPassword) throws SerialException, TimeoutException, ResponseException {
    final SmsAtRunWhiteListCommand command = new SmsAtRunWhiteListCommand(atCommander, action, index, entryType, phonenumberOrPassword);
    command.set();
  }

  public void setGeneralPurposeInputOutput(final int pin, final int simSlot, final int direction)
      throws SerialException, TimeoutException, ResponseException {
    final GeneralPurposeInputOutputCommand command = new GeneralPurposeInputOutputCommand(atCommander, pin, simSlot, direction);
    command.set();
  }

  public void setGeneralPurposeInputOutput(final int pin, final int simSlot, final int direction, final boolean save)
      throws SerialException, TimeoutException, ResponseException {
    final GeneralPurposeInputOutputCommand command = new GeneralPurposeInputOutputCommand(atCommander, pin, simSlot, direction, save);
    command.set();
  }

  public void execCommand(final String s, final int timeout) throws SerialException, TimeoutException {
    try {
      final SimpleCommand command = new SimpleCommand(atCommander, s);
      command.setTimeout(timeout);
      command.set();
    } catch (final ResponseException e) {
      LOG.warn("Could not execute the command {} with timeout {}: {}", s, timeout, e.getMessage());
    }
  }

  public void setGpsPower(final boolean enable) throws SerialException, TimeoutException, ResponseException {
    final GpsPowerCommand command = new GpsPowerCommand(atCommander, enable);
    command.set();
  }

  public void setGpsRestore() throws SerialException, TimeoutException, ResponseException {
    final GpsRestoreCommand command = new GpsRestoreCommand(atCommander);
    command.set();
  }

  public void setGpsReset(final GpsResetType type) throws SerialException, TimeoutException, ResponseException {
    final GpsResetCommand command = new GpsResetCommand(atCommander, type);
    command.set();
  }

  public void setGpsNmeaUnsolicited(final boolean enable, final boolean gga, final boolean gll, final boolean gsa, final boolean gsv,
                                    final boolean rmc, final boolean vtg)
      throws SerialException, TimeoutException, ResponseException {
    final GpsNmeaUnsolicitedCommand command = new GpsNmeaUnsolicitedCommand(atCommander, enable);
    command.setGga(gga);
    command.setGll(gll);
    command.setGsa(gsa);
    command.setGsv(gsv);
    command.setRmc(rmc);
    command.setVtg(vtg);
    command.set();
  }

  public void setGprsAuthentication(final Authentication authentication) throws SerialException, TimeoutException, ResponseException {
    final GprsAuthenticationTypeCommand command = new GprsAuthenticationTypeCommand(atCommander, authentication);
    command.set();
  }

  public void setEasyIpAuthentication(final Authentication authentication) throws SerialException, TimeoutException, ResponseException {
    final EasyGprsActivationAuthenticationCommand command = new EasyGprsActivationAuthenticationCommand(atCommander, authentication);
    command.set();
  }

  public EasyGprsActivationResponse setActivateContext(final int cid, final boolean activate, final long timeout)
      throws SerialException, TimeoutException, ResponseException {
    final EasyGprsActivationCommand command = new EasyGprsActivationCommand(atCommander, cid, activate);
    command.setTimeout(timeout);
    final EasyGprsActivationResponse resp = command.set();
    final String ipAddress = resp.getIpAddress();
    if (ipAddress != null) {
      LOG.info("SGACT returned the address '{}'", ipAddress);
    }
    return resp;
  }

  public EasyGprsActivationReadResponse readActivateContext() throws SerialException, TimeoutException, ResponseException {
    final EasyGprsActivationCommand command = new EasyGprsActivationCommand(atCommander);
    final EasyGprsActivationReadResponse resp = command.read();
    final List<ContextStatus> contextStatuses = resp.getContextStatuses();
    for (final ContextStatus contextStatus : contextStatuses) {
      LOG.info("SGACT cid {} with state {}", contextStatus.getCid(), contextStatus.getStatus());
    }
    return resp;
  }

  public EasyGprsActivationResponse setActivateContext(final int cid, final boolean activate, final String userId, final String password, final long timeout)
      throws SerialException, TimeoutException, ResponseException {
    final EasyGprsActivationCommand command = new EasyGprsActivationCommand(atCommander, cid, activate, userId, password);
    command.setTimeout(timeout);
    final EasyGprsActivationResponse response = command.set();
    final String ipAddress = response.getIpAddress();
    if (ipAddress != null) {
      LOG.info("SGACT returned the IP address '{}'", ipAddress);
    }
    return response;
  }

  public void setActivateConfigureContext(final int cid, final int retry, final int delay, final int urcMode)
      throws SerialException, TimeoutException, ResponseException {
    final EasyGprsActivationConfigurationCommand command = new EasyGprsActivationConfigurationCommand(atCommander, cid, retry);
    command.setDelay(delay);
    command.setUrcMode(urcMode);
    command.set();
  }

  public GprsResponse setActivateGprsContext(final boolean activate) throws SerialException, TimeoutException, ResponseException {
    final GprsCommand command = new GprsCommand(atCommander, activate);
//    command.setTimeout(time);
    final GprsResponse response = command.set();
    final String ipAddress = response.getIpAddress();
    if (ipAddress != null) {
      LOG.info("GPRS context has IP address {}", ipAddress);
    }
    return response;
  }

  public GprsStatusResponse getActivateGprsContext() throws SerialException, TimeoutException, ResponseException {
    final GprsCommand command = new GprsCommand(atCommander);
    final GprsStatusResponse response = command.read();
    LOG.info("Current GPRS context is {}", (response.getActive() ? "active" : "not active"));
    return response;
  }

  public void setAuthenticationUserId(final String userId) throws SerialException, TimeoutException, ResponseException {
    final AuthenticationUserIdCommand command = new AuthenticationUserIdCommand(atCommander, userId);
    command.set();
  }

  public void setAuthenticationPassword(final String password) throws SerialException, TimeoutException, ResponseException {
    final AuthenticationPasswordCommand command = new AuthenticationPasswordCommand(atCommander, password);
    command.set();
  }

  public GprsAct[] getGprsActStatus() throws SerialException, TimeoutException, ResponseException {
    final GprsActivateCommand command = new GprsActivateCommand(atCommander);
    final GprsActStatusResponse response = command.read();
    return response.getGprsActives();
  }

  public GprsAct getGprsActStatus(final int cid) throws SerialException, TimeoutException, ResponseException {
    final GprsActivateCommand command = new GprsActivateCommand(atCommander);
    final GprsActStatusResponse response = command.read();
    for (GprsAct gprsAct : response.getGprsActives()) {
      if (gprsAct.getCid() == cid) {
        return gprsAct;
      }
    }
    throw new IllegalStateException("The GPRS status for cid " + cid + " was not found");
  }

  public GprsDataVolume[] getGprsDataVolume(final GprsDataVolumeMode mode) throws SerialException, TimeoutException, ResponseException {
    final GprsDataVolumeCommand command = new GprsDataVolumeCommand(atCommander, mode);
    final GprsDataVolumeResponse response = command.set();
    return response.getGprsDataVolumes();
  }

  public void socketConfig(final int socketId, final int cid, final int packetSize,
                           final int inactivityTimeout, final int connectivityTimeout, final int sendTimeout)
      throws SerialException, TimeoutException, ResponseException {
    // with default send timeout of 255
    final SocketConfigCommand command = new SocketConfigCommand(atCommander, socketId, cid, packetSize,
        inactivityTimeout, connectivityTimeout, sendTimeout);
    command.set();
  }

  public void socketConfigurationExtended(final int socketId, final SringMode sringMode, final DataMode receivedDataMode,
                                          final int keepalive, final boolean listenAutoResponse, final DataMode sendDataMode)
      throws SerialException, TimeoutException, ResponseException {
    final SocketConfigurationExtendedCommand command = new SocketConfigurationExtendedCommand(atCommander, socketId,
        sringMode, receivedDataMode, keepalive, listenAutoResponse, sendDataMode);
    command.set();
  }

  public void socketConfigurationExtended2(final int socketId, final int bufferStart, final int abortConnAttempt,
                                           final int sringLen, final int sringTo, final int noCarrierMode)
      throws SerialException, TimeoutException, ResponseException {
    final SocketConfigurationExtended2Command command = new SocketConfigurationExtended2Command(atCommander, socketId,
        bufferStart, abortConnAttempt, sringLen, sringTo, noCarrierMode);
    command.set();
  }

  public void socketConfigurationExtended3(final int socketId, final int immediateResponse, final int closureTypeCmdModeEnabling)
      throws SerialException, TimeoutException, ResponseException {
    final SocketConfigurationExtended3Command command = new SocketConfigurationExtended3Command(atCommander, socketId,
        immediateResponse, closureTypeCmdModeEnabling);
    command.set();
  }

  public void socketConfigurationExtended3(final int socketId, final int immediateResponse,
                                           final int closureTypeCmdModeEnabling, final int fastRing)
      throws SerialException, TimeoutException, ResponseException {
    final SocketConfigurationExtended3Command command = new SocketConfigurationExtended3Command(atCommander, socketId,
        immediateResponse, closureTypeCmdModeEnabling);
    command.setFastRing(fastRing);
    command.set();
  }

  public SocketStatus[] socketStatus() throws SerialException, TimeoutException, ResponseException {
    final SocketStatusCommand command = new SocketStatusCommand(atCommander);
    final SocketStatusResponse response = command.set();
    return response.getSocketStatuses();
  }

  public SocketStatus[] socketStatus(final int socketId) throws SerialException, TimeoutException, ResponseException {
    final SocketStatusCommand command = new SocketStatusCommand(atCommander, socketId);
    final SocketStatusResponse response = command.set();
    return response.getSocketStatuses();
  }

  public SocketType[] socketType() throws SerialException, TimeoutException, ResponseException {
    final SocketTypeCommand command = new SocketTypeCommand(atCommander);
    final SocketTypeResponse response = command.set();
    return response.getSocketTypes();
  }

  public SocketType[] socketType(final int socketId) throws SerialException, TimeoutException, ResponseException {
    final SocketTypeCommand command = new SocketTypeCommand(atCommander, socketId);
    final SocketTypeResponse response = command.set();
    return response.getSocketTypes();
  }

  public SocketInformation[] socketInformation() throws SerialException, TimeoutException, ResponseException {
    final SocketInformationCommand command = new SocketInformationCommand(atCommander);
    final SocketInformationResponse resp = command.set();
    return resp.getSocketInformations();
  }

  public SocketInformation[] socketInformation(final int socketId) throws SerialException, TimeoutException, ResponseException {
    final SocketInformationCommand command = new SocketInformationCommand(atCommander, socketId);
    final SocketInformationResponse resp = command.set();
    return resp.getSocketInformations();
  }

  public SocketLastClosure[] socketLastClosure(final int socketId) throws SerialException, TimeoutException, ResponseException {
    final SocketLastClosureCommand command = new SocketLastClosureCommand(atCommander, socketId);
    final SocketLastClosureResponse response = command.set();
    return response.getSocketLastClosures();
  }

  public SocketLastClosure[] socketLastClosure() throws SerialException, TimeoutException, ResponseException {
    final SocketLastClosureCommand command = new SocketLastClosureCommand(atCommander);
    final SocketLastClosureResponse response = command.set();
    return response.getSocketLastClosures();
  }

  public void socketDial(final int socketId, final TransmissionProtocol transmissionProtocol,
                         final int remotePort, final String remoteAddress,
                         final ClosureType closureType, final int localPort)
      throws SerialException, TimeoutException, ResponseException {
    final SocketDialCommand command = new SocketDialCommand(atCommander, socketId, transmissionProtocol, remotePort, remoteAddress);
    command.setClosureType(closureType);
    command.setLocalPort(localPort);
    command.setConnectionMode(ConnectionMode.COMMAND);
    command.setTimeout(300000);
    command.set();
  }

  public void socketListen(final boolean mode, final int listenPort, final int closureType)
      throws SerialException, TimeoutException, ResponseException {
    final SocketListenCommand command = new SocketListenCommand(atCommander, mode, TransmissionProtocol.UDP, listenPort, closureType);
    command.set();
  }

  public void socketListenUdp(final int socketId, final boolean listenState, final int listenPort)
      throws SerialException, TimeoutException, ResponseException {
    final SocketListenUdpCommand command = new SocketListenUdpCommand(atCommander, socketId, listenState, listenPort);
    command.set();
  }

  public void socketAccept(final int socketId, final ConnectionMode connectionMode) throws SerialException, TimeoutException, ResponseException {
    final SocketAcceptCommand command = new SocketAcceptCommand(atCommander, socketId, connectionMode);
    command.set();
  }

  public void socketSend(final int socketId, final byte[] data) throws SerialException, TimeoutException, ResponseException {
    final SocketSendCommand command = new SocketSendCommand(atCommander, socketId, data);
    command.set();
  }

  public void socketSendExtended(final int socketId, final int length, final byte[] data)
      throws SerialException, TimeoutException, ResponseException {
    final SocketSendExtendedCommand command = new SocketSendExtendedCommand(atCommander, socketId, length, data);
    command.set();
  }

  public void socketSendUdp(final int socketId, final String remoteIp, final int remotePort, final byte[] data)
      throws SerialException, TimeoutException, ResponseException {
    final SocketSendUdpCommand command = new SocketSendUdpCommand(atCommander, socketId, remoteIp, remotePort, data);
    command.set();
  }

  public byte[] socketReceive(final int socketId, final int maximumBytes) throws SerialException, TimeoutException, ResponseException {
    final SocketReceiveCommand command = new SocketReceiveCommand(atCommander, socketId, maximumBytes);
    final SocketReceiveResponse response = command.set();
    return Util.hexToByteArray(response.getData());
  }

  public byte[] socketReceive(final int socketId, final int maximumBytes, final boolean udpInfo)
      throws SerialException, TimeoutException, ResponseException {
    final SocketReceiveCommand command = new SocketReceiveCommand(atCommander, socketId, maximumBytes, udpInfo);
    final SocketReceiveResponse response = command.set();
    return Util.hexToByteArray(response.getData());
  }

  public void socketShutdown(final int socketId) throws SerialException, TimeoutException, ResponseException {
    final SocketShutdownCommand command = new SocketShutdownCommand(atCommander, socketId);
    command.set();
  }

  public Ping[] setPing(final String ipAddress, final int numberOfRequests, final int length, final int timeout,
                        final int timeToLive) throws SerialException, TimeoutException, ResponseException {
    final PingCommand command = new PingCommand(atCommander, ipAddress, numberOfRequests, length, timeout, timeToLive);
    command.setTimeout(120000);
    final PingResponse response = command.set();
    return response.getPings();
  }

  public NetworkDns[] getNetworkDns(final int cid) throws SerialException, TimeoutException, ResponseException {
    final NetworkDnsCommand command = new NetworkDnsCommand(atCommander, new int[]{ cid });
    final NetworkDnsResponse response = command.set();
    return response.getNetworkDnses();
  }

  public void setHttpConfigure(final int profileId, final String serverAddress, final int serverPort,
                               final int authenticationType, final String username,
                               final String password, final boolean sslEnabled, final int timeout,
                               final int cid)
      throws SerialException, TimeoutException, ResponseException {
    final HttpConfigurationCommand command = new HttpConfigurationCommand(atCommander, profileId, serverAddress,
        serverPort, authenticationType, username, password, sslEnabled, timeout, cid);
    command.set();
  }

  public void httpQuery(final int profileId, final int command, final String resource) throws SerialException, TimeoutException, ResponseException {
    final HttpQueryCommand httpQueryCommand = new HttpQueryCommand(atCommander, profileId, command, resource);
    httpQueryCommand.set();
  }

  public List<String> httpReceive(final int profileId) throws SerialException, TimeoutException, ResponseException {
    final HttpReceiveCommand httpReceiveCommand = new HttpReceiveCommand(atCommander, profileId);
    final AnyResponse response = httpReceiveCommand.set();
    final List<String> httpLines = response.getLines();
    return httpLines;
  }

  public List<String> httpReceive(final int profileId, final int maximumBytes)
      throws SerialException, TimeoutException, ResponseException {
    final HttpReceiveCommand httpReceiveCommand = new HttpReceiveCommand(atCommander, profileId, maximumBytes);
    final AnyResponse response = httpReceiveCommand.set();
    final List<String> httpLines = response.getLines();
    return httpLines;
  }

  public SendMessageResponse sendTelitSmsAsPdu(final String destination, final String text)
      throws SerialException, TimeoutException, ResponseException {
    if (!MessageMode.PDU.equals(this.messageMode)) {
      throw new IllegalStateException("The modem must be put in PDU message mode first");
    }
    LOG.info("SELINT:{} SMSMODE:{}", interfaceStyle, smsMode);
    if (interfaceStyle == 0 || interfaceStyle == 1) {
      if (smsMode == 0) {
        LOG.info("Fallback to ETSI mode");
        super.sendSmsAsPdu(destination, text);
      }
    }

    final SmsPdu pdu = new SmsPdu();
    pdu.setDestination(destination);
    pdu.setText(text);
    pdu.setDcs((byte) 0x00); // GSM-7bit
    pdu.setPid((byte) 0x00);
    pdu.setHeader(new byte[]{});
    // Telit version
    final SendMessageCommand command = new SendMessageCommand(atCommander, messageMode);
    command.setPdu(pdu.create());
    command.setLength(pdu.getMessageLength());
    final SendMessageResponse response = command.set();
    LOG.info("The SMS was send to {}: reference {}", destination, response.getReference());
    return response;
  }

  public SendMessageResponse sendTelitSmsAsText(final String destination, final String text) throws SerialException, TimeoutException, ResponseException {
    if (!MessageMode.TEXT.equals(this.messageMode)) {
      throw new IllegalStateException("The modem must be put in TEXT message mode first");
    }
    LOG.info("SELINT:{} SMSMODE:{}", interfaceStyle, smsMode);
    if (interfaceStyle == 0 || interfaceStyle == 1) {
      if (smsMode == 0) {
        LOG.info("Fallback to ETSI mode");
        super.sendSmsAsText(destination, text);
      }
    }

    // Telit version
    final SendMessageCommand command = new SendMessageCommand(atCommander, messageMode, characterSet);
    command.setText(text);
    command.setAddress(destination);
    final SendMessageResponse response = command.set();
    LOG.info("The SMS was send to {}: reference {}", destination, response.getReference());
    return response;
  }

  public int sendSms(final String destination, final String text) throws SerialException, TimeoutException, ResponseException {
    LOG.info("SELINT:{} SMSMODE:{} MESSAGE_MODE:{}", interfaceStyle, smsMode, messageMode);

    if (interfaceStyle == 0 || interfaceStyle == 1) {
      if (smsMode == 0) {
        LOG.info("Fallback to ETSI mode");
        super.sendSmsAsText(destination, text);
      }
    }

    switch (messageMode) {
      case PDU:
        final SmsPdu pdu = new SmsPdu();
        pdu.setDestination(destination);
        pdu.setText(text);
        pdu.setDcs((byte) 0x00); // GSM-7bit
        pdu.setPid((byte) 0x00);
        pdu.setHeader(new byte[]{});

        // Telit version
        final SendMessageCommand commandPdu = new SendMessageCommand(atCommander, messageMode);
        commandPdu.setPdu(pdu.create());
        commandPdu.setLength(pdu.getMessageLength());
        final SendMessageResponse responsePdu = commandPdu.set();
        LOG.info("The SMS was send to {}: reference {}", destination, responsePdu.getReference());
        return responsePdu.getReference();

      case TEXT:
        final SendMessageCommand commandText = new SendMessageCommand(atCommander, messageMode, characterSet);
        commandText.setText(text);
        commandText.setAddress(destination);
        final SendMessageResponse responseText = commandText.set();
        LOG.info("The SMS was send to {}: reference {}", destination, responseText.getReference());
        return responseText.getReference();
      default:
        throw new IllegalStateException("Unknown message mode");
    }
  }

  public void setAccessTechnology(final AccessTechnology accessTechnology) {
    this.accessTechnology = accessTechnology;
  }
}
