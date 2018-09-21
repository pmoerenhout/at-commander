package com.github.pmoerenhout.atcommander.module.telit.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module.telit.types.ActiveSet;

public class ReadNetworkStatusResponseTest extends BaseCommandTest {

// #RFSTS: "204 04",1001,-80,00DE,01,5,19,10,2,9878,"204047930002270","vodafone NL",1,2
// #RFSTS: ,1001,-77,00DE,01,5,19,10,2,9878,"204047930002270","",0,2
// #RFSTS: "204 08",14,-83,0CBC,00,5,19,10,,BEE9,"204047930002270","NL KPN",0,2
// #RFSTS: "204 16",848,-89,03F8,,0,19,29,,14BB,"204047930002270","T-Mobile NL",0,3
// #RFSTS:"204 08",14,-80,0CBC,00,5,19,11,,BEE9,"204230090192154","NL KPN",0,2
// #RFSTS:"204 04",1020,-63,00DE,FF,0,19,29,,C1AD,"204047930219620","vodafone NL(",1,2
// #RFSTS:"000 00",-1,-47,FFFF,FF,255,19,27,,FFFF,"204086527040379","NL KPN",1,2
// #RFSTS: ,32767,-47,,,19,27,,,"204230090288415","",0,2

  @Test
  public void testReadNetworkStatusResponse() {
    final AtResponse response = createOkAtResponse("#RFSTS: \"204 04\",1001,-80,00DE,01,5,19,10,2,9878,\"204047930002270\",\"vodafone NL\",1,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 04", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(1001), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-80), readNetworkStatusResponse.getRssi());
    assertEquals("00DE", readNetworkStatusResponse.getLocationAreaCode());
    assertEquals("01", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(5), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(10), readNetworkStatusResponse.getRadioResourceState());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("9878", readNetworkStatusResponse.getCellId());
    assertEquals("204047930002270", readNetworkStatusResponse.getImsi());
    assertEquals("vodafone NL", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void testReadNetworkStatusResponseWithNoNetwork() {
    final AtResponse response = createOkAtResponse("#RFSTS: ,1331,-77,0FDE,01,6,18,10,2,9877,\"204047930002270\",\"\",0,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertNull(readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(1331), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-77), readNetworkStatusResponse.getRssi());
    assertEquals("0FDE", readNetworkStatusResponse.getLocationAreaCode());
    assertEquals("01", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(6), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(18), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(10), readNetworkStatusResponse.getRadioResourceState());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("9877", readNetworkStatusResponse.getCellId());
    assertEquals("204047930002270", readNetworkStatusResponse.getImsi());
    assertNull(readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void testReadNetworkStatusResponseTMobile() {
    final AtResponse response = createOkAtResponse("#RFSTS: \"204 16\",848,-89,03F8,,0,19,29,,14BB,\"204047930002270\",\"T-Mobile NL\",0,3");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 16", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(848), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-89), readNetworkStatusResponse.getRssi());
    assertEquals("03F8", readNetworkStatusResponse.getLocationAreaCode());
    assertNull(readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(29), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("14BB", readNetworkStatusResponse.getCellId());
    assertEquals("204047930002270", readNetworkStatusResponse.getImsi());
    assertEquals("T-Mobile NL", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(3), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void testReadNetworkStatusResponseKPN() {
    final AtResponse response = createOkAtResponse("#RFSTS:\"204 08\",14,-80,0CBC,00,5,19,11,,BEE9,\"204230090192154\",\"NL KPN\",0,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 08", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(14), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-80), readNetworkStatusResponse.getRssi());
    assertEquals("0CBC", readNetworkStatusResponse.getLocationAreaCode());
    assertEquals("00", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(5), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(11), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("BEE9", readNetworkStatusResponse.getCellId());
    assertEquals("204230090192154", readNetworkStatusResponse.getImsi());
    assertEquals("NL KPN", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void testReadNetworkStatusResponseVodafone() {
    final AtResponse response = createOkAtResponse("#RFSTS:\"204 04\",1020,-63,00DE,FF,0,19,29,,C1AD,\"204047930219620\",\"vodafone NL(\",1,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 04", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(1020), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-63), readNetworkStatusResponse.getRssi());
    assertEquals("00DE", readNetworkStatusResponse.getLocationAreaCode());
    assertEquals("FF", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(29), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("C1AD", readNetworkStatusResponse.getCellId());
    assertEquals("204047930219620", readNetworkStatusResponse.getImsi());
    assertEquals("vodafone NL(", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void testReadNetworkStatusResponseVodafone2() {
    final AtResponse response = createOkAtResponse("#RFSTS:\"000 00\",-1,-47,FFFF,FF,255,19,27,,FFFF,\"204086527040379\",\"NL KPN\",1,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("000 00", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(-1), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-47), readNetworkStatusResponse.getRssi());
    assertEquals("FFFF", readNetworkStatusResponse.getLocationAreaCode());
    assertEquals("FF", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(255), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(27), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("FFFF", readNetworkStatusResponse.getCellId());
    assertEquals("204086527040379", readNetworkStatusResponse.getImsi());
    assertEquals("NL KPN", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void testReadNetworkStatusResponseKpn() {
    final AtResponse response = createOkAtResponse("#RFSTS:\"204 08\",7,-92,0CBC,FF,5,19,29,,BEE8,\"204086527040379\",\"NL KPN\",1,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 08", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(7), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-92), readNetworkStatusResponse.getRssi());
    assertEquals("0CBC", readNetworkStatusResponse.getLocationAreaCode());
    assertEquals("FF", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(5), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(29), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("BEE8", readNetworkStatusResponse.getCellId());
    assertEquals("204086527040379", readNetworkStatusResponse.getImsi());
    assertEquals("NL KPN", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

//  //#RFSTS: "000 00",65535,65535,-127.5,-255,-0,FFFF,FF,0,256,18,4,,000,2DDC1B3,"204047930219620","vodafone NL",1,0
//  @Test
//  public void testReadNetworkStatusResponseTelit() {
//    final String line = "#RFSTS: \"000 00\",1001,-80,00DE,01,-0,FFFF,FF,0,256,18,4,,000,2DDC1B3,\"204047930219620\",\"vodafone NL\",1,0";
////    final String line = "#RFSTS: \"204 04\",1001,-80,00DE,01,5,19,10,2,9878,\"204047930002270\",\"vodafone NL\",1,2";
////    final String line = "#RFSTS: \"000 00\",65535,65535,-127.5,-255,-0,FFFF,FF,0,256,18,4,,000,2DDC1B3,\"204047930219620\",\"vodafone NL\",1,0";
//
//    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(new String[]{ line });
//
//    assertEquals("000 00", readNetworkStatusResponse.getPlmn());
//    assertEquals(Integer.valueOf(7), readNetworkStatusResponse.getArfcn());
//    assertEquals(Integer.valueOf(-92), readNetworkStatusResponse.getRssi());
//    assertEquals("0CBC", readNetworkStatusResponse.getLocationAreaCode());
//    assertEquals("FF", readNetworkStatusResponse.getRoutingAreaCode());
//    assertEquals(Integer.valueOf(5), readNetworkStatusResponse.getTxPower());
//    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
//    assertEquals(Integer.valueOf(29), readNetworkStatusResponse.getRadioResourceState());
//    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
//    assertEquals("BEE8", readNetworkStatusResponse.getCellId());
//    assertEquals("204086527040379", readNetworkStatusResponse.getImsi());
//    assertEquals("NL KPN", readNetworkStatusResponse.getOperatorName());
//    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
//    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());
//
//    assertEquals(line, readNetworkStatusResponse.toString());
//  }

//  @Test
//  public void testReadNetworkStatusResponseVodafoneActual() {
//    final String line = "#RFSTS: ,,,,,,,,-38,64,19,4,2,000,2DDC1C3,\"204047930002270\",\"\",0,1,0,0,-0.0";
//
//    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(new String[]{ line });
//
//    assertNull(readNetworkStatusResponse.getPlmn());
//    assertNull(readNetworkStatusResponse.getArfcn());
//    assertNull(readNetworkStatusResponse.getRssi());
//    assertNull(readNetworkStatusResponse.getLocationAreaCode());
////    assertNull(readNetworkStatusResponse.getRoutingAreaCode());
////    assertEquals(Integer.valueOf(5), readNetworkStatusResponse.getTxPower());
////    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
////    assertEquals(Integer.valueOf(29), readNetworkStatusResponse.getRadioResourceState());
////    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
////    assertEquals("BEE8", readNetworkStatusResponse.getCellId());
//    assertEquals("204086527040379", readNetworkStatusResponse.getImsi());
//    assertEquals("NL KPN", readNetworkStatusResponse.getOperatorName());
//    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
//    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());
//
//    assertEquals(line, readNetworkStatusResponse.toString());
//  }

  @Test
  public void test_read_network_status_response_vodafone_incorrect_13_tokens() {
    final AtResponse response = createOkAtResponse("#RFSTS: ,32767,-47,,,19,27,,,\"204230090288415\",\"\",0,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertNull(readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(32767), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-47), readNetworkStatusResponse.getRssi());
    assertNull( readNetworkStatusResponse.getLocationAreaCode());
    assertNull( readNetworkStatusResponse.getRoutingAreaCode());
    assertNull(readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(27), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertNull(readNetworkStatusResponse.getCellId());
    assertEquals("204230090288415", readNetworkStatusResponse.getImsi());
    assertNull(readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void test_read_network_status_response_vodafone_incorrect_13_tokens_2() {
    final AtResponse response = createOkAtResponse("#RFSTS: \"204 04\",32767,-47,,,18,5,,,\"204230090288415\",\"vodafone NL\",1,0");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 04", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(32767), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-47), readNetworkStatusResponse.getRssi());
    assertNull( readNetworkStatusResponse.getLocationAreaCode());
    assertNull( readNetworkStatusResponse.getRoutingAreaCode());
    assertNull(readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(18), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(5), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertNull(readNetworkStatusResponse.getCellId());
    assertEquals("204230090288415", readNetworkStatusResponse.getImsi());
    assertEquals("vodafone NL", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void test_read_network_status_response_vodafone_3() {
    final AtResponse response = createOkAtResponse("#RFSTS: \"204 04\",998,-73,00DE,00,5,19,10,,FD4F,\"204047930330643\",\"vodafone NL\",1,2");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 04", readNetworkStatusResponse.getPlmn());
    assertEquals(Integer.valueOf(998), readNetworkStatusResponse.getArfcn());
    assertEquals(Integer.valueOf(-73), readNetworkStatusResponse.getRssi());
    assertEquals("00DE", readNetworkStatusResponse.getLocationAreaCode());
    assertEquals("00", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(5), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(10), readNetworkStatusResponse.getRadioResourceState());
    assertNull(readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("FD4F", readNetworkStatusResponse.getCellId());
    assertEquals("204047930330643", readNetworkStatusResponse.getImsi());
    assertEquals("vodafone NL", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getActiveBand());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void test_read_network_status_response_vodafone_umts() {
    final AtResponse response = createOkAtResponse("#RFSTS: \"204 04\",,,,,,,,0,64,0,4,2,,2DDC1C3,\"204047930002270\",\"vodafone NL\",1,0");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 04", readNetworkStatusResponse.getPlmn());
    assertNull(readNetworkStatusResponse.getUarfcn());
    assertNull(readNetworkStatusResponse.getRssi());
//    assertEquals("00DE", readNetworkStatusResponse.getLocationAreaCode());
//    assertEquals("00", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(64), readNetworkStatusResponse.getDrx());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(4), readNetworkStatusResponse.getRadioResourceState());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("2DDC1C3", readNetworkStatusResponse.getCellId());
    assertEquals("204047930002270", readNetworkStatusResponse.getImsi());
    assertEquals("vodafone NL", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(0), readNetworkStatusResponse.getNumberOfActiveSet());

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void test_read_network_status_response_vodafone_umts_2() {
    final AtResponse response = createOkAtResponse("#RFSTS: \"204 04\",,,,,,,,24,64,19,4,2,000,2DDBDAB,\"204047930002270\",\"vodafone NL\",1,1,10564,176,-1.5");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 04", readNetworkStatusResponse.getPlmn());
    assertNull(readNetworkStatusResponse.getUarfcn());
    assertNull(readNetworkStatusResponse.getRssi());
//    assertEquals("00DE", readNetworkStatusResponse.getLocationAreaCode());
//    assertEquals("00", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(24), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(64), readNetworkStatusResponse.getDrx());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(4), readNetworkStatusResponse.getRadioResourceState());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("2DDBDAB", readNetworkStatusResponse.getCellId());
    assertEquals("204047930002270", readNetworkStatusResponse.getImsi());
    assertEquals("vodafone NL", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getNumberOfActiveSet());

    final List<ActiveSet> activeSets = readNetworkStatusResponse.getActiveSets();
    assertEquals(1, activeSets.size());
    assertEquals(10564, activeSets.get(0).getUarfcn());
    assertEquals(176f, activeSets.get(0).getPsc(), 0);
    assertEquals(-1.5f, activeSets.get(0).getEcio(), 0);

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }

  @Test
  public void test_read_network_status_response_vodafone_umts_3() {
    final AtResponse response = createOkAtResponse("#RFSTS: \"204 04\",,,,,,,,24,64,19,4,2,000,2DDC1C3,\"204047930002270\",\"vodafone NL\",1,1,0,0,-0.0");

    final ReadNetworkStatusResponse readNetworkStatusResponse = new ReadNetworkStatusResponse(response);

    assertEquals("204 04", readNetworkStatusResponse.getPlmn());
    assertNull(readNetworkStatusResponse.getUarfcn());
    assertNull(readNetworkStatusResponse.getRssi());
//    assertEquals("00DE", readNetworkStatusResponse.getLocationAreaCode());
//    assertEquals("00", readNetworkStatusResponse.getRoutingAreaCode());
    assertEquals(Integer.valueOf(24), readNetworkStatusResponse.getTxPower());
    assertEquals(Integer.valueOf(64), readNetworkStatusResponse.getDrx());
    assertEquals(Integer.valueOf(19), readNetworkStatusResponse.getMobilityManagementState());
    assertEquals(Integer.valueOf(4), readNetworkStatusResponse.getRadioResourceState());
    assertEquals(Integer.valueOf(2), readNetworkStatusResponse.getNetworkOperatorMode());
    assertEquals("2DDC1C3", readNetworkStatusResponse.getCellId());
    assertEquals("204047930002270", readNetworkStatusResponse.getImsi());
    assertEquals("vodafone NL", readNetworkStatusResponse.getOperatorName());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getServiceDomain());
    assertEquals(Integer.valueOf(1), readNetworkStatusResponse.getNumberOfActiveSet());

    final List<ActiveSet> activeSets = readNetworkStatusResponse.getActiveSets();
    assertEquals(1, activeSets.size());
    assertEquals(0, activeSets.get(0).getUarfcn());
    assertEquals(0, activeSets.get(0).getPsc(), 0);
    assertEquals(-0.0f, activeSets.get(0).getEcio(), 0);

    assertEquals(response.getInformationalText().get(0), readNetworkStatusResponse.toString());
  }
}