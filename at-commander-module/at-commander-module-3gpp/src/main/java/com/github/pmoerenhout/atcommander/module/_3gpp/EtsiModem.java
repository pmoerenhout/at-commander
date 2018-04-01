package com.github.pmoerenhout.atcommander.module._3gpp;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.api.SerialException;
import com.github.pmoerenhout.atcommander.api.SerialInterface;
import com.github.pmoerenhout.atcommander.api.UnsolicitedPatternClass;
import com.github.pmoerenhout.atcommander.basic.exceptions.ResponseException;
import com.github.pmoerenhout.atcommander.basic.exceptions.TimeoutException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.CellularResultCodesCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.CellularResultCodesResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.CellularRingResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.DefinePdpContextCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.DeleteMessageCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.FacilityLockCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.FacilityLockResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsActivateCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsAttachCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsAttachResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsClassCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.GprsEventReportingResponse;
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
import com.github.pmoerenhout.atcommander.module._3gpp.commands.OperatorSelectionCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.OperatorSelectionResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.OperatorSelectionTestResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PdPAddressCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PdpAddressResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PinCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.PinResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ProductSerialNumberIdentificationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ProductSerialNumberIdentificationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RestrictedSimAccessCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RestrictedSimAccessResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RevisionIdentificationCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.RevisionIdentificationResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectBearerServiceTypeCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectServiceForMoSmsMessagesCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectTECharacterSetCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SelectTECharacterSetResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SendListMessagesCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SendMessageCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SendMessageResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ServiceCentreAddressCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ServiceCentreAddressResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ServiceReportingControlCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.ServiceReportingControlResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SettingsDateFormatCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SignalQualityCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.SignalQualityResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.WirelessNetworkCommand;
import com.github.pmoerenhout.atcommander.module._3gpp.commands.WirelessNetworkStatusResponse;
import com.github.pmoerenhout.atcommander.module._3gpp.sms.SmsPdu;
import com.github.pmoerenhout.atcommander.module._3gpp.types.FacilityStatus;
import com.github.pmoerenhout.atcommander.module._3gpp.types.ListMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.PdpAddress;
import com.github.pmoerenhout.atcommander.module._3gpp.types.SignalQuality;
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

public class EtsiModem extends V250 {

  // 3GPP TS 27.005

  // http://www.etsi.org/deliver/etsi_gts/07/0705/05.03.00_60/gsmts_0705v050300p.pdf
  // http://www.etsi.org/deliver/etsi_gts/07/0707/05.00.00_60/gsmts_0707v050000p.pdf
  // http://www.etsi.org/deliver/etsi_ts/127000_127099/127007/08.03.00_60/ts_127007v080300p.pdf

  public static final ArrayList<UnsolicitedPatternClass> UNSOLICITED_PATTERN_CLASS_LIST = new ArrayList<>(Arrays.asList(
      new UnsolicitedPatternClass(NetworkRegistrationResponse.UNSOLICITED_PATTERN1, NetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(NetworkRegistrationResponse.UNSOLICITED_PATTERN2, NetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN1, GprsNetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN2, GprsNetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN3, GprsNetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN4, GprsNetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN5, GprsNetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(GprsNetworkRegistrationResponse.UNSOLICITED_PATTERN6, GprsNetworkRegistrationResponse.class),
      new UnsolicitedPatternClass(GprsEventReportingResponse.UNSOLICITED_PATTERN, GprsEventReportingResponse.class),
      new UnsolicitedPatternClass(ServiceReportingControlResponse.UNSOLICITED_PATTERN, ServiceReportingControlResponse.class),
      new UnsolicitedPatternClass(CellularRingResponse.UNSOLICITED_PATTERN, CellularRingResponse.class)
  ));
  private static final Logger LOG = LoggerFactory.getLogger(EtsiModem.class);
  protected MessageMode messageMode;
  protected String characterSet;

  public EtsiModem(final SerialInterface serial) {
    // final String portName, final int portDataRate, final List<UnsolicitedPatternClass> unsolicitedPatterns,
    // final UnsolicitedResponseCallback event
    //super(serial, portName, portDataRate, ListUtils.union(UNSOLICITED_PATTERN_CLASS_LIST, unsolicitedPatterns), event);
    super(serial);
    UNSOLICITED_PATTERN_CLASS_LIST.forEach(u -> serial.addUnsolicited(u));
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

  public List<String> testSelectTECharacterSet() throws SerialException, TimeoutException, ResponseException {
    final SelectTECharacterSetCommand command = new SelectTECharacterSetCommand(atCommander);
    final SelectTECharacterSetResponse response = command.test();
    final List<String> characterSets = response.getCharacterSets();
    LOG.debug("TE character sets: {}", characterSets);
    return characterSets;
  }

  public String getSelectTECharacterSet() throws SerialException, TimeoutException, ResponseException {
    final SelectTECharacterSetCommand command = new SelectTECharacterSetCommand(atCommander);
    final SelectTECharacterSetResponse response = command.read();
    this.characterSet = response.getCharacterSet();
    return this.characterSet;
  }

  public void setSelectTECharacterSet(final String characterSet) throws SerialException, TimeoutException, ResponseException {
    final SelectTECharacterSetCommand command = new SelectTECharacterSetCommand(atCommander, characterSet);
    command.set();
    this.characterSet = characterSet;
  }

  public String getInternationalMobileSubscriberIdentity() throws SerialException, TimeoutException, ResponseException {
    final ImsiCommand command = new ImsiCommand(atCommander);
    final ImsiResponse response = command.set();
    return response.getImsi();
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
    final NetworkRegistrationCommand command = new NetworkRegistrationCommand(
        atCommander);
    return command.read();
  }

  public void setNetworkRegistration(final int mode) throws SerialException, TimeoutException, ResponseException {
    final NetworkRegistrationCommand command = new NetworkRegistrationCommand(atCommander, mode);
    command.set();
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

  public void setSelectServiceForMoSmsMessages(final int service) throws SerialException, TimeoutException, ResponseException {
    // 0=Packet Domain 1=circuit switched 2=Packet Domain preferred 3=circuit switched preferred
    final SelectServiceForMoSmsMessagesCommand command = new SelectServiceForMoSmsMessagesCommand(atCommander, service);
    command.set();
  }

  public void setMoreMessageToSend(final int mode) throws SerialException, TimeoutException, ResponseException {
    final MoreMessagesToSendCommand command = new MoreMessagesToSendCommand(atCommander, mode);
    command.set();
  }

  public ServiceCentreAddressResponse getServiceCentreAddress() throws SerialException, TimeoutException, ResponseException {
    final ServiceCentreAddressCommand command = new ServiceCentreAddressCommand(atCommander);
    final ServiceCentreAddressResponse response = command.set();
    LOG.info("SMSC address: {} type {}", response.getNumber(), response.getType());
    return response;
  }

  public List<ListMessage> getMessagesList(final MessageStatus status) throws SerialException, TimeoutException, ResponseException {
    final SendListMessagesCommand command = new SendListMessagesCommand(atCommander, messageMode, status);
    final ListMessagesResponse response = command.set();
    final List<ListMessage> messages = response.getMessageList();
    return messages;
  }

  public void deleteMessage(final int index, final int flag) throws SerialException, TimeoutException, ResponseException {
    final DeleteMessageCommand deleteMessageCommand = new DeleteMessageCommand(atCommander, index, flag);
    deleteMessageCommand.set();
  }

  public void deleteAllMessages() throws SerialException, TimeoutException, ResponseException {
    deleteMessage(1, 4);
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
    final SendMessageResponse response = command.set();
    LOG.info("The SMS was send to {}: reference {}", destination, response.getReference());
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
    LOG.info("The text SMS was send to {}: reference {}", destination, response.getReference());
    return response;
  }

  public byte[] getRestrictedSimAccess(final int command, final int fileId, final int p1, final int p2, final int p3)
      throws SerialException, TimeoutException, ResponseException {
    final RestrictedSimAccessCommand rsmCommand = new RestrictedSimAccessCommand(atCommander, command, fileId, p1, p2, p3);
    final RestrictedSimAccessResponse response = rsmCommand.set();
    final int sw1 = response.getSw1();
    final int sw2 = response.getSw2();
    final byte[] data = response.getData();
    LOG.info("SW:{} DATA:{}", String.format("%04X", (sw1 << 8) + sw2), Util.bytesToHexString(data));
    return data;
  }

  public byte[] getRestrictedSimAccess(final int command, final int fileId, final int p1, final int p2, final int p3, final byte[] data)
      throws SerialException, TimeoutException, ResponseException {
    final RestrictedSimAccessCommand rsmCommand = new RestrictedSimAccessCommand(atCommander, command, fileId, p1, p2, p3, data);
    final RestrictedSimAccessResponse response = rsmCommand.set();
    final int sw1 = response.getSw1();
    final int sw2 = response.getSw2();
    LOG.info("SW:{} DATA:{}", String.format("%04X", (sw1 << 8) + sw2), Util.bytesToHexString(data));
    return data;
  }

  public byte[] getForbiddenPlmn() throws SerialException, TimeoutException, ResponseException {
    return getRestrictedSimAccess(RestrictedSimAccessCommand.COMMAND_READ_BINARY, RestrictedSimAccessCommand.FILE_FPLMN, 0, 0, 12);
  }

  public byte[] setForbiddenPlmn(final byte[] data) throws SerialException, TimeoutException, ResponseException {
    return getRestrictedSimAccess(RestrictedSimAccessCommand.COMMAND_UPDATE_BINARY, RestrictedSimAccessCommand.FILE_FPLMN, 0, 0, 12, data);
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
        LOG.info("FacilityStatus: {} {}", fs.getStatus(), fs.getClazz());
      } else {
        LOG.info("FacilityStatus: {}", fs.getStatus());
      }
    }
    if (facilityStatuses.length == 1) {
      return facilityStatuses[0].getStatus() != 0;
    }
    return null;
  }

  public void setGprsClass(final String gprsClass) throws SerialException, TimeoutException, ResponseException {
    final GprsClassCommand command = new GprsClassCommand(atCommander, gprsClass);
    command.set();
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
      LOG.info("CID:{} ADDRESS:{}", pdpAddress.getCid(), pdpAddress.getAddress());
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

  public void getGprsNetworkRegistration() throws SerialException, TimeoutException, ResponseException {
    final GprsNetworkRegistrationCommand command = new GprsNetworkRegistrationCommand(atCommander);
    command.read();
  }
}
