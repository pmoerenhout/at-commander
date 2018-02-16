package org.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.atcommander.module.v250.enums.AccessTechnology;
import org.junit.Before;
import org.junit.Test;

public class ServInfoResponseTest extends BaseCommandTest {

  // #SERVINFO: 1020,-61,"vodafone NL","20404",61,00DE,00,1,,"II",01,6
  // #SERVINFO: 1020,-55,"vodafone NL","20404",61,00DE,00,0
  // #SERVINFO: 841,-95,"T-Mobile NL","20416",43,03F8,01,1,,"I",01,6
  // #SERVINFO: ,,"vodafone NL","20404",,00DE,64,3,,"II"
  // #SERVINFO: 65535,-0,"vodafone NL(","20404",65535,00DE,64,3,-255,"II",FF

//  UMTS network   #SERVINFO: <UARFCN>, <dBM>, <NetNameAsc>,<NetCode>, <PSC>,<LAC>,<DRX>,<SD>,<RSCP>, <NOM>,<RAC>

  @Before
  public void setUp() throws Exception {
    // BasicConfigurator.resetConfiguration();
    // BasicConfigurator.configure();
    // SimpleLogger logger = new SimpleLogger("","");
  }

  @Test
  public void testServInfoResponseGSM() {
    final AtResponse response = createOkAtResponse("#SERVINFO: 1020,-55,\"Vodafone NL(\",\"20404\",61,00DE,00,0");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(new Integer(1020), servInfoResponse.getBcchArfcn());
    assertEquals(new Integer(-55), servInfoResponse.getDbm());
    assertEquals("Vodafone NL(", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals("61", servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("00", servInfoResponse.getTimeAdvance());
    assertEquals(false, servInfoResponse.isGprsSupported());


    assertEquals(response.getInformationalText().get(0), servInfoResponse.toString());
  }

  @Test
  public void testServInfoResponseTMobile() {
    final AtResponse response = createOkAtResponse("#SERVINFO: 841,-95,\"T-Mobile NL\",\"20416\",43,03F8,01,1,,\"I\",01,6");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(new Integer(841), servInfoResponse.getBcchArfcn());
    assertEquals(new Integer(-95), servInfoResponse.getDbm());
    assertEquals("T-Mobile NL", servInfoResponse.getNetName());
    assertEquals("20416", servInfoResponse.getNetCode());
    assertEquals("43", servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("03F8", servInfoResponse.getLocationAreaCode());
    assertEquals("01", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());
  }

  @Test
  public void testServInfoResponse2() {
    final AtResponse response = createOkAtResponse("#SERVINFO: 1020,-61,\"vodafone NL(\",\"20404\",61,00DE,00,1,,\"II\",01,6");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(new Integer(1020), servInfoResponse.getBcchArfcn());
    assertEquals(new Integer(-61), servInfoResponse.getDbm());
    assertEquals("vodafone NL(", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals("61", servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("00", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());
  }

  @Test
  public void testServInfoResponse3() {
    final AtResponse response = createOkAtResponse("#SERVINFO: ,,\"vodafone NL\",\"20404\",,00DE,64,1,,\"II\"");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(null, servInfoResponse.getBcchArfcn());
    assertEquals(null, servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals(null, servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("64", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());
  }

  @Test
  public void testServInfoResponse4() {
    // #SERVINFO: ,,"vodafone NL","20404",,,256,1
    final AtResponse response = createOkAtResponse("#SERVINFO: ,,\"vodafone NL\",\"20404\",,,256,1");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(null, servInfoResponse.getBcchArfcn());
    assertEquals(null, servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals(null, servInfoResponse.getBaseStationIdentificationCode());
    assertEquals(null, servInfoResponse.getLocationAreaCode());
    assertEquals("256", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());
  }

  @Test
  public void testServInfoResponse5() {
    // #SERVINFO: ,,"vodafone NL","20404",,03X02D3,64,1,,"II"
    final AtResponse response = createOkAtResponse("#SERVINFO: ,,\"vodafone NL\",\"20404\",,03X02D3,64,1,,\"II\"");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(null, servInfoResponse.getBcchArfcn());
    assertEquals(null, servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals(null, servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("03X02D3", servInfoResponse.getLocationAreaCode());
    assertEquals("64", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());
  }

  @Test
  public void testServInfoResponse6() {

    // #SERVINFO: 1001,-91,"vodafone NL","20404",FFFF7,00DE,00,1,,"II",03,6
    final AtResponse response = createOkAtResponse("#SERVINFO: 1001,-91,\"vodafone NL\",\"20404\",FFFF7,00DE,00,1,,\"II\",03,6");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(new Integer(1001), servInfoResponse.getBcchArfcn());
    assertEquals(new Integer(-91), servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals("FFFF7", servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("00", servInfoResponse.getTimeAdvance());
    assertEquals(Boolean.TRUE, servInfoResponse.isGprsSupported());
    assertEquals(null, servInfoResponse.getPbArfcn());
    assertEquals("II", servInfoResponse.getNetworkOperationMode());
    assertEquals("03", servInfoResponse.getRoutingAreaColourCode());
    assertEquals(new Integer(6), servInfoResponse.getPriorityAccessThreshold());
  }

  @Test
  public void testServInfoResponse7() {

    final AtResponse response = createOkAtResponse("#SERVINFO: ,,\"vodafone NL\",\"20404\",,00DE,64,3,,\"II\"");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(null, servInfoResponse.getBcchArfcn());
    assertEquals(null, servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals(null, servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("64", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());  // 3
    assertEquals(null, servInfoResponse.getPbArfcn());
    assertEquals("II", servInfoResponse.getNetworkOperationMode());
    assertEquals(null, servInfoResponse.getRoutingAreaColourCode());
    assertEquals(null, servInfoResponse.getPriorityAccessThreshold());
  }

  @Test
  public void testServInfoResponse8() {

    final AtResponse response = createOkAtResponse("#SERVINFO: 0,,\"NL KPN\",\"20408\",0,0000,0,1,-0,,00");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(new Integer(0), servInfoResponse.getBcchArfcn());
    assertEquals(null, servInfoResponse.getDbm());
    assertEquals("NL KPN", servInfoResponse.getNetName());
    assertEquals("20408", servInfoResponse.getNetCode());
    assertEquals("0", servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("0000", servInfoResponse.getLocationAreaCode());
    assertEquals("0", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());  // 3
    assertEquals("-0", servInfoResponse.getPbArfcn());
    assertEquals(null, servInfoResponse.getNetworkOperationMode());
    assertEquals("00", servInfoResponse.getRoutingAreaColourCode());
    assertEquals(null, servInfoResponse.getPriorityAccessThreshold());
  }

  @Test
  public void testServInfoResponse9() {

    final AtResponse response = createOkAtResponse("#SERVINFO: ,,\"NL KPN\",\"20408\",,00DE,64,1,,\"II\",01");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(null, servInfoResponse.getBcchArfcn());
    assertEquals(null, servInfoResponse.getDbm());
    assertEquals("NL KPN", servInfoResponse.getNetName());
    assertEquals("20408", servInfoResponse.getNetCode());
    assertEquals(null, servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("64", servInfoResponse.getTimeAdvance());
    assertEquals(true, servInfoResponse.isGprsSupported());  // 3
    assertEquals(null, servInfoResponse.getPbArfcn());
    assertEquals("II", servInfoResponse.getNetworkOperationMode());
    assertEquals("01", servInfoResponse.getRoutingAreaColourCode());
    assertEquals(null, servInfoResponse.getPriorityAccessThreshold());
  }

  @Test
  public void testServInfoResponseUmtsKpn() {

    final AtResponse response = createOkAtResponse("#SERVINFO: 3011,-79,\"NL KPN\",\"20408\",90,00DE,64,1,-87");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.UTRAN);

    assertEquals(new Integer(3011), servInfoResponse.getUmtsArfcn());
    assertEquals(new Integer(-79), servInfoResponse.getDbm());
    assertEquals("NL KPN", servInfoResponse.getNetName());
    assertEquals("20408", servInfoResponse.getNetCode());
    assertEquals("90", servInfoResponse.getPrimarySynchronisationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("64", servInfoResponse.getDiscontinuousReceptionCycleLength());
    assertEquals("1", servInfoResponse.getServiceDomain());
    assertEquals(new Integer(-87), servInfoResponse.getReceivedSignalCodePower());
    assertEquals(null, servInfoResponse.getNetworkOperationMode());
    assertEquals(null, servInfoResponse.getRoutingAreaColourCode());
    assertEquals(null, servInfoResponse.getPriorityAccessThreshold());
  }

  @Test
  public void testServInfoResponse11() {

    final AtResponse response = createOkAtResponse("#SERVINFO: 1001,-82,\"vodafone NL\",\"20404\",FFFF7,18EC,00,0");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(new Integer(1001), servInfoResponse.getBcchArfcn());
    assertEquals(new Integer(-82), servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());

    assertEquals("FFFF7", servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("18EC", servInfoResponse.getLocationAreaCode());
    assertEquals("00", servInfoResponse.getTimeAdvance());
    assertEquals(false, servInfoResponse.isGprsSupported());
    assertEquals(null, servInfoResponse.getPbArfcn());
    assertEquals(null, servInfoResponse.getNetworkOperationMode());
    assertEquals(null, servInfoResponse.getRoutingAreaColourCode());
    assertEquals(null, servInfoResponse.getPriorityAccessThreshold());
  }

  @Test
  public void testServInfoResponseUmts1() {

    final AtResponse response = createOkAtResponse("1012,-83,\"vodafone NL\",\"20404\",00,00DE,00,0");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.UTRAN);

    assertEquals(new Integer(1012), servInfoResponse.getUmtsArfcn());
    assertEquals(new Integer(-83), servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals("00", servInfoResponse.getPrimarySynchronisationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("00", servInfoResponse.getDiscontinuousReceptionCycleLength());
    assertEquals("0", servInfoResponse.getServiceDomain());
    assertEquals(null, servInfoResponse.getReceivedSignalCodePower());
    assertEquals(null, servInfoResponse.getNetworkOperationMode());
    assertEquals(null, servInfoResponse.getRoutingAreaColourCode());
  }

  @Test
  public void testServInfoResponseUmts2() {

    final AtResponse response = createOkAtResponse("10612,-84,\"vodafone NL\",\"20404\",176,00DE,64,1,-88,\"II\",00");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.UTRAN);

    assertEquals(new Integer(10612), servInfoResponse.getUmtsArfcn());
    assertEquals(new Integer(-84), servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals("176", servInfoResponse.getPrimarySynchronisationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("64", servInfoResponse.getDiscontinuousReceptionCycleLength());
    assertEquals("1", servInfoResponse.getServiceDomain());
    assertEquals(new Integer(-88), servInfoResponse.getReceivedSignalCodePower());
    assertEquals("II", servInfoResponse.getNetworkOperationMode());
    assertEquals("00", servInfoResponse.getRoutingAreaColourCode());
  }

  @Test
  public void test_servinfo_response_ge910() {

    final AtResponse response = createOkAtResponse("#SERVINFO: 1020,-63,\"vodafone NL\",\"20404\",61,00DE,00,0");

    final ServInfoResponse servInfoResponse = new ServInfoResponse(response, AccessTechnology.GSM);

    assertEquals(new Integer(1020), servInfoResponse.getBcchArfcn());
    assertEquals(new Integer(-63), servInfoResponse.getDbm());
    assertEquals("vodafone NL", servInfoResponse.getNetName());
    assertEquals("20404", servInfoResponse.getNetCode());
    assertEquals("61", servInfoResponse.getBaseStationIdentificationCode());
    assertEquals("00DE", servInfoResponse.getLocationAreaCode());
    assertEquals("00", servInfoResponse.getTimeAdvance());
    assertFalse(servInfoResponse.isGprsSupported());
  }

}