package com.github.pmoerenhout.atcommander.module._3gpp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.basic.commands.EmptyResponse;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.CallingLineIdentificationPresentationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.CallingLineIdentificationPresentationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.CellularResultCodesCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.CellularResultCodesResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ClockCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ClockResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.DefinePdpContextCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.DeleteMessageCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.FacilityLockCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.FacilityLockResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.FunctionalityCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsActivateCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsAttachCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsAttachResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsMobileStationClassCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsMobileStationClassResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsNetworkRegistrationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsNetworkRegistrationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ImsiCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ImsiResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ListMessagesResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.MessageFormatCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.MessageFormatResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.MobileEquipmentErrorCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ModelIdentificationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ModelIdentificationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.MoreMessagesToSendCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NewMessageAcknowledgementCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NewMessageIndicationsCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.NewMessageIndicationsResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.OperatorSelectionCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.OperatorSelectionResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.OperatorSelectionTestResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PdPAddressCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PdpAddressResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PhoneActivityStatusCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PhoneActivityStatusResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PinCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PinResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PreferredMessageStorageCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PreferredMessageStorageResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ProductSerialNumberIdentificationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ProductSerialNumberIdentificationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ReadMessageCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ReadMessageResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RestrictedSimAccessCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RestrictedSimAccessResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RevisionIdentificationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RevisionIdentificationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectBearerServiceTypeCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectMessageServiceCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectMessageServiceResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectServiceForMoSmsMessagesCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectServiceForMoSmsMessagesResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectTECharacterSetCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectTECharacterSetResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SendListMessagesCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SendMessageCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SendMessageResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ServiceCentreAddressCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ServiceCentreAddressResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ServiceReportingControlCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SettingsDateFormatCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ShowTextModeParametersCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SignalQualityCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SignalQualityResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SubscriberNumberCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SubscriberNumberResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.UnstructuredSupplementaryServiceDataCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.WirelessNetworkCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.WirelessNetworkStatusResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.sms.SmsPdu;
import com.github.pmoerenhout.atcommander.module._3gpp.types.FacilityStatus;
import com.github.pmoerenhout.atcommander.module._3gpp.types.IndexMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.PdpAddress;
import com.github.pmoerenhout.atcommander.module._3gpp.types.SignalQuality;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.CallingLineIdentificationPresentationUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.CellularRingUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.ConnectedLineIdentificationPresentationUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.GprsEventReportingUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.GprsNetworkRegistrationUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.MessageTerminatingIndicationUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.MessageTerminatingUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.NetworkRegistrationUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.ServiceReportingControlUnsolicited;
import com.github.pmoerenhout.atcommander.module._3gpp.unsolicited.UnstructuredSupplementaryServiceDataUnsolicited;
import com.github.pmoerenhout.atcommander.module.v250.V250;
import com.github.pmoerenhout.atcommander.module.v250.commands.ManufacturerIdentificationCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.ManufacturerIdentificationResponse;
import com.github.pmoerenhout.atcommander.module.v250.commands.SelectModeCommand;
import com.github.pmoerenhout.atcommander.module.v250.commands.TestResponse;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;
import com.github.pmoerenhout.atcommander.module.v250.enums.ConnectionElement;
import com.github.pmoerenhout.atcommander.module.v250.enums.DataName;
import com.github.pmoerenhout.atcommander.module.v250.enums.DataRate;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;
import com.github.pmoerenhout.atcommander.module.v250.enums.OperatorSelectionMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.PdpType;
import com.github.pmoerenhout.atcommander.module.v250.enums.PinStatus;
import com.github.pmoerenhout.atcommander.module.v250.enums.WirelessNetwork;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EtsiModem extends V250 {

  // 3GPP TS 27.005

  // http://www.etsi.org/deliver/etsi_gts/07/0705/05.03.00_60/gsmts_0705v050300p.pdf
  // http://www.etsi.org/deliver/etsi_gts/07/0707/05.00.00_60/gsmts_0707v050000p.pdf
  // http://www.etsi.org/deliver/etsi_ts/127000_127099/127007/08.03.00_60/ts_127007v080300p.pdf

  public static final ArrayList<UnsolicitedPatternClass> UNSOLICITED_PATTERN_CLASS_LIST = new ArrayList<>(Arrays.asList(
      new UnsolicitedPatternClass(NetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, NetworkRegistrationUnsolicited.class),
      new UnsolicitedPatternClass(GprsNetworkRegistrationUnsolicited.UNSOLICITED_PATTERN, GprsNetworkRegistrationUnsolicited.class),
      new UnsolicitedPatternClass(GprsEventReportingUnsolicited.UNSOLICITED_PATTERN, GprsEventReportingUnsolicited.class),
      new UnsolicitedPatternClass(ServiceReportingControlUnsolicited.UNSOLICITED_PATTERN, ServiceReportingControlUnsolicited.class),
      new UnsolicitedPatternClass(CellularRingUnsolicited.UNSOLICITED_PATTERN, CellularRingUnsolicited.class),
      new UnsolicitedPatternClass(MessageTerminatingUnsolicited.UNSOLICITED_PATTERN, MessageTerminatingUnsolicited.class, 1),
      new UnsolicitedPatternClass(MessageTerminatingIndicationUnsolicited.UNSOLICITED_PATTERN, MessageTerminatingIndicationUnsolicited.class),
      new UnsolicitedPatternClass(CallingLineIdentificationPresentationUnsolicited.UNSOLICITED_PATTERN, CallingLineIdentificationPresentationUnsolicited.class),
      new UnsolicitedPatternClass(ConnectedLineIdentificationPresentationUnsolicited.UNSOLICITED_PATTERN,
          ConnectedLineIdentificationPresentationUnsolicited.class),
      new UnsolicitedPatternClass(UnstructuredSupplementaryServiceDataUnsolicited.UNSOLICITED_PATTERN,
          UnstructuredSupplementaryServiceDataUnsolicited.class)
  ));

  public AccessTechnology accessTechnology = null;
  protected MessageMode messageMode;
  protected String characterSet;

  public EtsiModem(final SerialInterface serial) {
    super(serial);
    atCommander.addFinalResponseFactory(new FinalFactory3gpp());
    UNSOLICITED_PATTERN_CLASS_LIST.forEach(serial::addUnsolicited);
  }

  public String getManufacturerIdentification() throws SerialException, TimeoutException, ResponseException {
    final ManufacturerIdentificationCommand command = new ManufacturerIdentificationCommand(atCommander);
    final ManufacturerIdentificationResponse resp = command.set();
    return resp.getManufacturer();
  }

  public String getModelIdentification() throws SerialException, TimeoutException, ResponseException {
    final ModelIdentificationCommand command = new ModelIdentificationCommand(atCommander);
    final ModelIdentificationResponse resp = command.set();
    return resp.getModel();
  }

  public String getRevisionIdentification() throws SerialException, TimeoutException, ResponseException {
    final RevisionIdentificationCommand command = new RevisionIdentificationCommand(atCommander);
    final RevisionIdentificationResponse resp = command.set();
    return resp.getRevision();
  }

  public String getProductSerialNumberIdentification() throws SerialException, TimeoutException, ResponseException {
    final ProductSerialNumberIdentificationCommand command = new ProductSerialNumberIdentificationCommand(atCommander);
    final ProductSerialNumberIdentificationResponse response = command.set();
    return response.getProductSerialNumber();
  }

  public List<String> getTeCharacterSets() throws SerialException, TimeoutException, ResponseException {
    final SelectTECharacterSetCommand command = new SelectTECharacterSetCommand(atCommander);
    final SelectTECharacterSetResponse response = command.test();
    final List<String> characterSets = response.getCharacterSets();
    return characterSets;
  }

  public String getTeCharacterSet() throws SerialException, TimeoutException, ResponseException {
    final SelectTECharacterSetCommand command = new SelectTECharacterSetCommand(atCommander);
    final SelectTECharacterSetResponse response = command.read();
    this.characterSet = response.getCharacterSet();
    return this.characterSet;
  }

  public void setTeCharacterSet(final String characterSet) throws SerialException, TimeoutException, ResponseException {
    final SelectTECharacterSetCommand command = new SelectTECharacterSetCommand(atCommander, characterSet);
    command.set();
    this.characterSet = characterSet;
  }

  public String getInternationalMobileSubscriberIdentity() throws SerialException, TimeoutException, ResponseException {
    final ImsiCommand command = new ImsiCommand(atCommander);
    final ImsiResponse response = command.set();
    return response.getImsi();
  }

  public int getPhoneActivityStatus() throws SerialException, TimeoutException, ResponseException {
    final PhoneActivityStatusCommand command = new PhoneActivityStatusCommand(atCommander);
    final PhoneActivityStatusResponse response = command.set();
    return response.getStatus();
  }

  public WirelessNetwork getWirelessNetwork() throws SerialException, TimeoutException, ResponseException {
    final WirelessNetworkCommand command = new WirelessNetworkCommand(atCommander);
    final WirelessNetworkStatusResponse response = command.read();
    return response.getWirelessNetwork();
  }

  public void setWirelessNetwork(final WirelessNetwork wirelessNetwork) throws SerialException, TimeoutException, ResponseException {
    final WirelessNetworkCommand command = new WirelessNetworkCommand(atCommander, wirelessNetwork);
    command.set();
  }

  public List<String> testWirelessNetwork() throws SerialException, TimeoutException, ResponseException {
    final WirelessNetworkCommand command = new WirelessNetworkCommand(atCommander);
    final TestResponse response = command.test();
    return response.getValues();
  }

  public CellularResultCodesResponse getCellularResultCodes() throws SerialException, TimeoutException, ResponseException {
    final CellularResultCodesCommand command = new CellularResultCodesCommand(atCommander);
    CellularResultCodesResponse response = command.read();
    return response;
  }

  public void setCellularResultCodes(final boolean extended) throws SerialException, TimeoutException, ResponseException {
    final CellularResultCodesCommand command = new CellularResultCodesCommand(atCommander, extended);
    command.set();
    return;
  }

  public CellularResultCodesResponse testCellularResultCodes() throws SerialException, TimeoutException, ResponseException {
    final CellularResultCodesCommand command = new CellularResultCodesCommand(atCommander);
    return command.test();
  }

  public void setServiceReportingControl(final int mode) throws SerialException, TimeoutException, ResponseException {
    final ServiceReportingControlCommand command = new ServiceReportingControlCommand(atCommander, mode);
    command.set();
  }

  public void setSettingsDateFormat(final int mode) throws SerialException, TimeoutException, ResponseException {
    final SettingsDateFormatCommand command = new SettingsDateFormatCommand(atCommander, mode);
    command.set();
  }

  public void setSettingsDateFormat(final int mode, final int auxMode) throws SerialException, TimeoutException, ResponseException {
    final SettingsDateFormatCommand command = new SettingsDateFormatCommand(atCommander, mode, auxMode);
    command.set();
  }

  public void setSelectBearerServiceType(final DataRate dataRate, final DataName dataName, final ConnectionElement connectionElement)
      throws SerialException, ResponseException, TimeoutException {
    final SelectBearerServiceTypeCommand command = new SelectBearerServiceTypeCommand(atCommander, dataRate, dataName, connectionElement);
    command.set();
    return;
  }

  public void setCsd() throws SerialException, TimeoutException, ResponseException {
    final SelectBearerServiceTypeCommand command = new SelectBearerServiceTypeCommand(
        atCommander, DataRate.V110_9600_BPS, DataName.ASYNCHRONOUS, ConnectionElement.NON_TRANSPARANT);
    command.set();
    return;
  }

  public NetworkRegistrationResponse getNetworkRegistration() throws SerialException, TimeoutException, ResponseException {
    final NetworkRegistrationCommand command = new NetworkRegistrationCommand(atCommander);
    return command.read();
  }

  public void setNetworkRegistration(final int mode) throws SerialException, TimeoutException, ResponseException {
    final NetworkRegistrationCommand command = new NetworkRegistrationCommand(atCommander, mode);
    command.set();
  }

  public NetworkRegistrationResponse getNetworkRegistration(final long timeout) throws SerialException, TimeoutException, ResponseException {
    final NetworkRegistrationCommand command = new NetworkRegistrationCommand(atCommander);
    command.setTimeout(timeout);
    return command.read();
  }

  public NetworkRegistrationResponse getNetworkRegistrations() throws SerialException, TimeoutException, ResponseException {
    final NetworkRegistrationCommand command = new NetworkRegistrationCommand(atCommander);
    return command.test();
  }

  public void setOperatorSelection(final OperatorSelectionMode mode, final String mccMnc) throws SerialException, TimeoutException, ResponseException {
    final OperatorSelectionCommand command = new OperatorSelectionCommand(atCommander, mode, mccMnc);
    command.set();
  }

  public void setOperatorSelection(final OperatorSelectionMode mode, final String mccMnc, final AccessTechnology accessTechnology)
      throws SerialException, TimeoutException, ResponseException {
    final OperatorSelectionCommand command = new OperatorSelectionCommand(atCommander, mode, mccMnc, accessTechnology);
    command.set();
  }

  public OperatorSelectionResponse getOperatorSelection() throws SerialException, TimeoutException, ResponseException {
    final OperatorSelectionCommand command = new OperatorSelectionCommand(atCommander);
    return command.read();
  }

  public void setOperatorSelection(final OperatorSelectionMode mode) throws SerialException, TimeoutException, ResponseException {
    final OperatorSelectionCommand command = new OperatorSelectionCommand(atCommander, mode);
    command.set();
  }

  public OperatorSelectionTestResponse testOperators() throws SerialException, TimeoutException, ResponseException {
    final OperatorSelectionCommand command = new OperatorSelectionCommand(atCommander);
    return command.test();
  }

  public void setMobileEquipmentError(final int mode) throws SerialException, TimeoutException, ResponseException {
    final MobileEquipmentErrorCommand command = new MobileEquipmentErrorCommand(atCommander, mode);
    command.set();
  }

  public SubscriberNumberResponse getSubscriberNumber() throws SerialException, TimeoutException, ResponseException {
    final SubscriberNumberCommand command = new SubscriberNumberCommand(atCommander);
    return command.set();
  }

  public SignalQuality getSignalQuality() throws SerialException, TimeoutException, ResponseException {
    final SignalQualityCommand command = new SignalQualityCommand(atCommander);
    final SignalQualityResponse response = command.set();
    return new SignalQuality(response.getRssi(), response.getBer());
  }

  public void setSelectMode(final int mode) throws SerialException, TimeoutException, ResponseException {
    final SelectModeCommand command = new SelectModeCommand(atCommander, mode);
    command.set();
  }

  public MessageMode getMessageMode() throws SerialException, TimeoutException, ResponseException {
    final MessageFormatCommand command = new MessageFormatCommand(atCommander, messageMode);
    MessageFormatResponse response = command.read();
    this.messageMode = MessageMode.fromInt(response.getFormat());
    return this.messageMode;
  }

  public void setMessageMode(final MessageMode messageMode) throws SerialException, TimeoutException, ResponseException {
    final MessageFormatCommand command = new MessageFormatCommand(atCommander, messageMode);
    command.set();
    this.messageMode = messageMode;
  }

  public void setMessageModeConditional(final MessageMode messageMode) throws SerialException, TimeoutException, ResponseException {
    if (!this.messageMode.equals(messageMode)) {
      final MessageFormatCommand command = new MessageFormatCommand(atCommander, messageMode);
      command.set();
      this.messageMode = messageMode;
    }
  }

  public int getServiceForMoSmsMessages() throws SerialException, TimeoutException, ResponseException {
    // 0=Packet Domain 1=circuit switched 2=Packet Domain preferred 3=circuit switched preferred
    final SelectServiceForMoSmsMessagesCommand command = new SelectServiceForMoSmsMessagesCommand(atCommander);
    final SelectServiceForMoSmsMessagesResponse response = command.read();
    return response.getService();
  }

  public void setServiceForMoSmsMessages(final int service) throws SerialException, TimeoutException, ResponseException {
    // 0=Packet Domain 1=circuit switched 2=Packet Domain preferred 3=circuit switched preferred
    final SelectServiceForMoSmsMessagesCommand command = new SelectServiceForMoSmsMessagesCommand(atCommander, service);
    command.set();
  }

  public List<Integer> getServicesForMoSmsMessages() throws SerialException, TimeoutException, ResponseException {
    // 0=Packet Domain 1=circuit switched 2=Packet Domain preferred 3=circuit switched preferred
    final SelectServiceForMoSmsMessagesCommand command = new SelectServiceForMoSmsMessagesCommand(atCommander);
    final SelectServiceForMoSmsMessagesResponse response = command.test();
    return response.getServices();
  }

  public void setMoreMessageToSend(final int mode) throws SerialException, TimeoutException, ResponseException {
    final MoreMessagesToSendCommand command = new MoreMessagesToSendCommand(atCommander, mode);
    command.set();
  }

  public void setServiceCentreAddress(final String number, final int type) throws SerialException, TimeoutException, ResponseException {
    final ServiceCentreAddressCommand command = new ServiceCentreAddressCommand(atCommander, number, type);
    command.set();
  }

  public ServiceCentreAddressResponse getServiceCentreAddress() throws SerialException, TimeoutException, ResponseException {
    final ServiceCentreAddressCommand command = new ServiceCentreAddressCommand(atCommander);
    final ServiceCentreAddressResponse response = command.read();
    log.info("SMSC address: {} type {}", response.getNumber(), response.getType());
    return response;
  }

  public void setServiceCentreAddress(final String number) throws SerialException, TimeoutException, ResponseException {
    final ServiceCentreAddressCommand command = new ServiceCentreAddressCommand(atCommander, number);
    command.set();
  }

  public void setPreferredMessageStorage(final String mem1) throws SerialException, TimeoutException, ResponseException {
    final PreferredMessageStorageCommand command = new PreferredMessageStorageCommand(atCommander, mem1);
    command.set();
  }

  public void setPreferredMessageStorage(final String mem1, final String mem2) throws SerialException, TimeoutException, ResponseException {
    final PreferredMessageStorageCommand command = new PreferredMessageStorageCommand(atCommander, mem1, mem2);
    command.set();
  }

  public void setPreferredMessageStorage(final String mem1, final String mem2, final String mem3) throws SerialException, TimeoutException, ResponseException {
    final PreferredMessageStorageCommand command = new PreferredMessageStorageCommand(atCommander, mem1, mem2, mem3);
    command.set();
  }

  public PreferredMessageStorageResponse getPreferredMessageStorage() throws SerialException, TimeoutException, ResponseException {
    final PreferredMessageStorageCommand command = new PreferredMessageStorageCommand(atCommander);
    return command.read();
  }

  public List<IndexMessage> getMessagesList(final MessageStatus status) throws SerialException, TimeoutException, ResponseException {
    final SendListMessagesCommand command = new SendListMessagesCommand(atCommander, messageMode, status);
    final ListMessagesResponse response = command.set();
    final List<IndexMessage> messages = response.getIndexMessages();
    return messages;
  }

  public void deleteMessage(final int index, final int flag) throws SerialException, TimeoutException, ResponseException {
    final DeleteMessageCommand deleteMessageCommand = new DeleteMessageCommand(atCommander, index, flag);
    deleteMessageCommand.set();
  }

  public void deleteAllMessages() throws SerialException, TimeoutException, ResponseException {
    deleteMessage(1, 4);
  }

  public void setNewMessageIndications(final int mode) throws SerialException, TimeoutException, ResponseException {
    final NewMessageIndicationsCommand newMessageIndicationsCommand = new NewMessageIndicationsCommand(atCommander, mode);
    newMessageIndicationsCommand.set();
  }

  public void setNewMessageIndications(final int mode, final int mt) throws SerialException, TimeoutException, ResponseException {
    final NewMessageIndicationsCommand newMessageIndicationsCommand = new NewMessageIndicationsCommand(atCommander, mode, mt);
    newMessageIndicationsCommand.set();
  }

  public void setNewMessageIndications(final int mode, final int mt, final int bm) throws SerialException, TimeoutException, ResponseException {
    final NewMessageIndicationsCommand newMessageIndicationsCommand = new NewMessageIndicationsCommand(atCommander, mode, mt, bm);
    newMessageIndicationsCommand.set();
  }

  public void setNewMessageIndications(final int mode, final int mt, final int bm, final int ds, final int bfr)
      throws SerialException, TimeoutException, ResponseException {
    final NewMessageIndicationsCommand newMessageIndicationsCommand = new NewMessageIndicationsCommand(atCommander, mode, mt, bm, ds, bfr);
    newMessageIndicationsCommand.set();
  }

  public NewMessageIndicationsResponse getNewMessageIndications() throws SerialException, TimeoutException, ResponseException {
    final NewMessageIndicationsCommand newMessageIndicationsCommand = new NewMessageIndicationsCommand(atCommander);
    return newMessageIndicationsCommand.read();
  }

  public SendMessageResponse sendPdu(final int lengthTpLayer, final String pdu)
      throws SerialException, TimeoutException, ResponseException {
    if (!MessageMode.PDU.equals(this.messageMode)) {
      throw new IllegalStateException("The modem must be put in PDU message mode first");
    }
    final SendMessageCommand command = new SendMessageCommand(atCommander, messageMode);
    command.setLength(lengthTpLayer);
    command.setPdu(pdu);
    log.info("LENGTH:{}, PDU[{}]:{}", lengthTpLayer, pdu.length(), pdu);
    final SendMessageResponse response = command.set();
    log.info("The SMS was send: reference {}", response.getReference());
    return response;
  }

  public SendMessageResponse sendSmsAsPdu(final String destination, final String text)
      throws SerialException, TimeoutException, ResponseException {
    if (!MessageMode.PDU.equals(this.messageMode)) {
      throw new IllegalStateException("The modem must be put in PDU message mode first");
    }
    final SmsPdu pdu = new SmsPdu();
    pdu.setDestination(destination);
    pdu.setText(text);
    pdu.setDcs((byte) 0x00); // GSM-7bit
    pdu.setPid((byte) 0x00);
    pdu.setHeader(new byte[]{});
    final SendMessageCommand command = new SendMessageCommand(atCommander, messageMode);
    command.setPdu(pdu.create());
    command.setLength(pdu.getMessageLength());
    log.info("PDU[{}]:{} LENGTH:{}", command.getPdu().length(), command.getPdu(), command.getLength());
    final SendMessageResponse response = command.set();
    log.info("The SMS was send to {}: reference {}", destination, response.getReference());
    return response;
  }

  public SendMessageResponse sendSmsAsText(final String destination, final String text)
      throws SerialException, TimeoutException, ResponseException {
    if (!MessageMode.TEXT.equals(this.messageMode)) {
      throw new IllegalStateException("The modem must be put in TEXT message mode first");
    }
    final SendMessageCommand command = new SendMessageCommand(atCommander, messageMode, characterSet);
    command.setAddress(destination);
    command.setTypeOfAddress(145);
    command.setText(text);
    final SendMessageResponse response = command.set();
    log.debug("The text SMS was send to {}: reference {}", destination, response.getReference());
    return response;
  }

  public ReadMessageResponse readSms(final int index)
      throws SerialException, TimeoutException, ResponseException {
    final ReadMessageCommand command = new ReadMessageCommand(atCommander, messageMode, index);
    final ReadMessageResponse response = command.set();
    return response;
  }

  public ReadMessageResponse readSmsAsPdu(final int index)
      throws SerialException, TimeoutException, ResponseException {
    if (!MessageMode.PDU.equals(this.messageMode)) {
      throw new IllegalStateException("The modem must be put in PDU message mode first");
    }
    final ReadMessageCommand command = new ReadMessageCommand(atCommander, messageMode, index);
    final ReadMessageResponse response = command.set();
    log.debug("The PDU SMS read {}: {}", index, response.getMessage());
    return response;
  }

  public void setShowTextModeParameters(final int show)
      throws SerialException, TimeoutException, ResponseException {
    final ShowTextModeParametersCommand command = new ShowTextModeParametersCommand(atCommander, show);
    command.set();
  }

  public byte[] getRestrictedSimAccess(final int command, final int fileId, final int p1, final int p2, final int p3)
      throws SerialException, TimeoutException, ResponseException {
    final RestrictedSimAccessCommand rsmCommand = new RestrictedSimAccessCommand(atCommander, command, fileId, p1, p2, p3);
    final RestrictedSimAccessResponse response = rsmCommand.set();
    final int sw1 = response.getSw1();
    final int sw2 = response.getSw2();
    final byte[] data = response.getData();
    log.info("SW:{} DATA:{}", String.format("%04X", (sw1 << 8) + sw2), Util.bytesToHexString(data));
    return data;
  }

  public byte[] getRestrictedSimAccess(final int command, final int fileId, final int p1, final int p2, final int p3, final byte[] data)
      throws SerialException, TimeoutException, ResponseException {
    final RestrictedSimAccessCommand rsmCommand = new RestrictedSimAccessCommand(atCommander, command, fileId, p1, p2, p3, data);
    final RestrictedSimAccessResponse response = rsmCommand.set();
    final int sw1 = response.getSw1();
    final int sw2 = response.getSw2();
    log.info("SW:{} DATA:{}", String.format("%04X", (sw1 << 8) + sw2), Util.bytesToHexString(data));
    return data;
  }

  public byte[] getForbiddenPlmn() throws SerialException, TimeoutException, ResponseException {
    return getRestrictedSimAccess(RestrictedSimAccessCommand.COMMAND_READ_BINARY, RestrictedSimAccessCommand.FILE_FPLMN, 0, 0, 12);
  }

  public byte[] setForbiddenPlmn(final byte[] data) throws SerialException, TimeoutException, ResponseException {
    return getRestrictedSimAccess(RestrictedSimAccessCommand.COMMAND_UPDATE_BINARY, RestrictedSimAccessCommand.FILE_FPLMN, 0, 0, 12, data);
  }

  public byte[] getLocation() throws SerialException, TimeoutException, ResponseException {
    return getRestrictedSimAccess(RestrictedSimAccessCommand.COMMAND_READ_BINARY, RestrictedSimAccessCommand.FILE_LOCATION, 0, 0, 11);
  }

  public FacilityLockResponse setFacilityLock(final String facility, final int mode) throws SerialException, TimeoutException, ResponseException {
    final FacilityLockCommand command = new FacilityLockCommand(atCommander, facility, mode);
    final FacilityLockResponse response = command.set();
    return response;
  }

  public Boolean queryFacility(final String facility) throws SerialException, TimeoutException, ResponseException {
    final FacilityLockResponse response = setFacilityLock(facility, 2);
    final FacilityStatus[] facilityStatuses = response.getFacilityStatus();
    for (final FacilityStatus fs : facilityStatuses) {
      if (fs.getClazz() != null) {
        log.info("FacilityStatus: {} {}", fs.getStatus(), fs.getClazz());
      } else {
        log.info("FacilityStatus: {}", fs.getStatus());
      }
    }
    if (facilityStatuses.length == 1) {
      return facilityStatuses[0].getStatus() != 0;
    }
    return null;
  }

  public GprsMobileStationClassResponse getGprsMobileStationClass() throws SerialException, TimeoutException, ResponseException {
    final GprsMobileStationClassCommand command = new GprsMobileStationClassCommand(atCommander);
    return command.read();
  }

  public void setGprsMobileStationClass(final String gprsMobileStationClass) throws SerialException, TimeoutException, ResponseException {
    final GprsMobileStationClassCommand command = new GprsMobileStationClassCommand(atCommander, gprsMobileStationClass);
    command.set();
  }

  public List<String> testGprsMobileStationClass() throws SerialException, TimeoutException, ResponseException {
    final GprsMobileStationClassCommand command = new GprsMobileStationClassCommand(atCommander);
    final TestResponse response = command.test();
    return response.getValues();
  }

  public GprsAttachResponse getGprsAttach() throws SerialException, TimeoutException, ResponseException {
    final GprsAttachCommand command = new GprsAttachCommand(atCommander);
    return command.read();
  }

  public void setGprsAttach(final boolean attach, final long timeout) throws SerialException, TimeoutException, ResponseException {
    final GprsAttachCommand command = new GprsAttachCommand(atCommander, attach);
    command.setTimeout(timeout);
    command.set();
  }

  public void setPin(final String pin) throws SerialException, TimeoutException, ResponseException {
    final PinCommand command = new PinCommand(atCommander, pin);
    command.set();
  }

  public void setActivatePdpContext(final int cid, final boolean activate) throws SerialException, TimeoutException, ResponseException {
    final GprsActivateCommand command = new GprsActivateCommand(atCommander, activate, cid);
    command.set();
  }

  public PdpAddressResponse getPdpAddresses(final Integer... cids) throws SerialException, TimeoutException, ResponseException {
    final PdPAddressCommand command = new PdPAddressCommand(atCommander, cids);
    final PdpAddressResponse response = command.set();
    for (final PdpAddress pdpAddress : response.getPdpAddresses()) {
      log.info("CID:{} ADDRESS:{}", pdpAddress.getCid(), pdpAddress.getAddress());
    }
    return response;
  }

  public void definePdpContext(final int cid, final PdpType pdpType, final String apn, final String pdpAddress, final boolean dataCompression,
                               final boolean headerCompression)
      throws SerialException, TimeoutException, ResponseException {
    final DefinePdpContextCommand command = new DefinePdpContextCommand(atCommander, cid, pdpType, apn, pdpAddress, dataCompression, headerCompression);
    command.set();
  }

  public void undefinePdpContext(final int cid) throws SerialException, TimeoutException, ResponseException {
    final DefinePdpContextCommand command = new DefinePdpContextCommand(atCommander, cid);
    command.set();
  }

  public PinStatus getPinStatus() throws SerialException, TimeoutException, ResponseException {
    final PinCommand command = new PinCommand(atCommander);
    final PinResponse response = command.read();
    return response.getStatus();
  }

  public CallingLineIdentificationPresentationResponse getCallingLineIdentificationPresentation() throws SerialException, TimeoutException, ResponseException {
    final CallingLineIdentificationPresentationCommand command = new CallingLineIdentificationPresentationCommand(atCommander);
    return command.read();
  }

  public void setCallingLineIdentificationPresentation(final boolean callingLineIndication) throws SerialException, TimeoutException, ResponseException {
    final CallingLineIdentificationPresentationCommand command = new CallingLineIdentificationPresentationCommand(atCommander, callingLineIndication);
    command.set();
  }

  public ClockResponse getClock() throws SerialException, TimeoutException, ResponseException {
    final ClockCommand command = new ClockCommand(atCommander);
    return command.read();
  }

  public EmptyResponse setClock(final String time) throws SerialException, TimeoutException, ResponseException {
    final ClockCommand command = new ClockCommand(atCommander, time);
    return command.set();
  }

  public GprsNetworkRegistrationResponse getGprsNetworkRegistration() throws SerialException, TimeoutException, ResponseException {
    final GprsNetworkRegistrationCommand command = new GprsNetworkRegistrationCommand(atCommander);
    return command.read();
  }

  public GprsNetworkRegistrationResponse getGprsNetworkRegistration(final long timeout) throws SerialException, TimeoutException, ResponseException {
    final GprsNetworkRegistrationCommand command = new GprsNetworkRegistrationCommand(atCommander);
    command.setTimeout(timeout);
    return command.read();
  }

  public void setGprsNetworkRegistration(final int mode) throws SerialException, TimeoutException, ResponseException {
    final GprsNetworkRegistrationCommand command = new GprsNetworkRegistrationCommand(atCommander, mode);
    command.set();
  }

  public void setAccessTechnology(final AccessTechnology accessTechnology) {
    this.accessTechnology = accessTechnology;
  }

  public void setUssd(final int type) throws SerialException, TimeoutException, ResponseException {
    final UnstructuredSupplementaryServiceDataCommand command = new UnstructuredSupplementaryServiceDataCommand(atCommander, type);
    command.set();
  }

  public void setSelectMessageService(final int service) throws SerialException, TimeoutException, ResponseException {
    final SelectMessageServiceCommand command = new SelectMessageServiceCommand(atCommander, service);
    command.set();
  }

  public SelectMessageServiceResponse getSelectMessageService() throws SerialException, TimeoutException, ResponseException {
    final SelectMessageServiceCommand command = new SelectMessageServiceCommand(atCommander);
    return command.read();
  }

  public void setNewMessageAcknowledgement() throws SerialException, TimeoutException, ResponseException {
    final NewMessageAcknowledgementCommand command = new NewMessageAcknowledgementCommand(atCommander);
    command.set();
  }

  public void setNewMessageAcknowledgement(final int ack) throws SerialException, TimeoutException, ResponseException {
    final NewMessageAcknowledgementCommand command = new NewMessageAcknowledgementCommand(atCommander, ack);
    command.set();
  }

  public void setUssd(final int type, final String ussdString) throws SerialException, TimeoutException, ResponseException {
    final UnstructuredSupplementaryServiceDataCommand command = new UnstructuredSupplementaryServiceDataCommand(atCommander, type, ussdString);
    command.set();
  }

  public void setUssd(final int type, final String ussdString, final int dcs) throws SerialException, TimeoutException, ResponseException {
    final UnstructuredSupplementaryServiceDataCommand command = new UnstructuredSupplementaryServiceDataCommand(atCommander, type, ussdString, dcs);
    command.set();
  }

  // Generic functions modem specific

  public String getIntegratedCircuitCardIdentification() throws SerialException, TimeoutException, ResponseException {
    throw new IllegalArgumentException("Please implement getIntegratedCircuitCardIdentification on modem class");
  }

  public void reboot() throws SerialException, TimeoutException, ResponseException {
    final FunctionalityCommand command = new FunctionalityCommand(atCommander, 1, true);
    command.set();

  }

}
