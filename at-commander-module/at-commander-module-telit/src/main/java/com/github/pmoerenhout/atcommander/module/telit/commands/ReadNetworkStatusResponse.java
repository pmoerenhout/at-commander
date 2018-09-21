package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module.telit.types.ActiveSet;

public class ReadNetworkStatusResponse extends BaseResponse implements Response {

  // #RFSTS: "204 04",1001,-80,00DE,01,5,19,10,2,9878,"204007930002270","vodafone NL",1,2
  // #RFSTS: ,1001,-77,00DE,01,5,19,10,2,9878,"204007930002270","",0,2
  // #RFSTS: "204 08",14,-83,0CBC,00,5,19,10,,BEE9,"204007930002270","NL KPN",0,2
  // #RFSTS: "204 16",848,-89,03F8,,0,19,29,,14BB,"204007930002270","T-Mobile NL",0,3
  // #RFSTS:"204 08",14,-80,0CBC,00,5,19,11,,BEE9,"204000090192154","NL KPN",0,2
  // #RFSTS:"000 00",-1,-47,FFFF,FF,255,19,27,,FFFF,"204086527040379","NL KPN",1,2
  // #RFSTS: ,32767,-47,,,19,27,,,"204000090288415","",0,2

  private static final Pattern PATTERN = Pattern.compile(
      "^\\#RFSTS:[ ]?\"([0-9 ]*)\",([-\\d]*),([-\\d]*),([-.A-Z0-9]*),([-.A-F0-9]*),([\\d]*),([\\d]*),([\\d]*),([ \\d]*),([0-9A-FX]*),\"([1-9][0-9]*)\",\"([a-zA-Z0-9-\\(\\)\\* ]*)\",([\\d]*),([\\d]*)$");

  private static final Pattern PATTERN2 = Pattern.compile(
      "^\\#RFSTS:[ ]?(),([-\\d]*),([-\\d]*),([-.A-Z0-9]*),([-.A-F0-9]*),([\\d]*),([\\d]*),([\\d]*),([\\d]*),([0-9A-FX]*),\"([1-9][0-9]*)\",\"([a-zA-Z0-9-\\(\\)\\* ]*)\",([\\d]*),([\\d]*)$");

  private String line;
  private String plmn;
  private Integer arfcn;
  private Integer rssi;
  private String locationAreaCode;
  private String routingAreaCode;
  private Integer txPower;
  private Integer mobilityManagementState;
  private Integer radioResourceState;
  private Integer networkOperatorMode;
  private String cellId;
  private String imsi;
  private String operatorName;
  private Integer serviceDomain;
  private Integer activeBand;
  private Integer uarfcn;
  private Float psc;
  private Float ecio;
  private Integer rscp;
  private Integer drx;
  private Integer blockErrorRate;
  private Integer numberOfActiveSet;

  private List<ActiveSet> activeSets = new ArrayList<>();

  public ReadNetworkStatusResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      line = informationalText.get(0);
      final String[] tokens = StringUtils.splitPreserveAllTokens(StringUtils.substringAfter(line, "#RFSTS:"), COMMA);
      final int tokensLength = tokens.length;
      // LOG.info("'{}': Split count: {}", line, tokens.length);
//      for (int i = 0; i < tokens.length; i++) {
//        LOG.info("{}: -{}-", i, tokens[i]);
//      }
      if (tokensLength == 13) {
        // GSM, incorrectly encoded
        if (StringUtils.isNotBlank(tokens[0])) {
          plmn = Util.removeQuotes(StringUtils.trim(tokens[0]));
        }
        arfcn = Integer.valueOf(tokens[1]);
        rssi = Integer.valueOf(tokens[2]);
        if (StringUtils.isNotBlank(tokens[3])) {
          locationAreaCode = tokens[3];
        }
        if (StringUtils.isNotBlank(tokens[4])) {
          routingAreaCode = tokens[4];
        }
        // Guess that it is the Tx Power which is missing
        // txPower = Integer.parseInt(tokens[4]);
        mobilityManagementState = Integer.valueOf(tokens[5]);
        if (StringUtils.isNotBlank(tokens[6])) {
          radioResourceState = Integer.valueOf(tokens[6]);
        }
        if (StringUtils.isNotBlank(tokens[7])) {
          networkOperatorMode = Integer.valueOf(tokens[7]);
        }
        if (StringUtils.isNotBlank(tokens[8])) {
          cellId = tokens[8];
        }
        imsi = Util.removeQuotes(tokens[9]);
        if (StringUtils.isNotBlank(tokens[10])) {
          String unquoted = Util.removeQuotes(tokens[10]);
          if (StringUtils.isNotBlank(unquoted)) {
            operatorName = unquoted;
          }
        }
        serviceDomain = Integer.valueOf(tokens[11]);
        activeBand = Integer.valueOf(tokens[12]);
        return;
      }
      if (tokensLength == 14) {
        // GSM
        if (StringUtils.isNotBlank(tokens[0])) {
          plmn = Util.removeQuotes(StringUtils.trim(tokens[0]));
        }
        if (StringUtils.isNotBlank(tokens[1])) {
          arfcn = Integer.valueOf(tokens[1]);
        }
        if (StringUtils.isNotBlank(tokens[2])) {
          rssi = Integer.valueOf(tokens[2]);
        }
        if (StringUtils.isNotBlank(tokens[3])) {
          locationAreaCode = tokens[3];
        }
        if (StringUtils.isNotBlank(tokens[4])) {
          routingAreaCode = tokens[4];
        }
        if (StringUtils.isNotBlank(tokens[5])) {
          txPower = Integer.valueOf(tokens[5]);
        }
        if (StringUtils.isNotBlank(tokens[6])) {
          mobilityManagementState = Integer.valueOf(tokens[6]);
        }
        if (StringUtils.isNotBlank(tokens[7])) {
          radioResourceState = Integer.valueOf(tokens[7]);
        }
        if (StringUtils.isNotBlank(tokens[8])) {
          networkOperatorMode = Integer.valueOf(tokens[8]);
        }
        if (StringUtils.isNotBlank(tokens[9])) {
          cellId = tokens[9];
        }
        imsi = Util.removeQuotes(tokens[10]);
        if (StringUtils.isNotBlank(tokens[11])) {
          String unquoted = Util.removeQuotes(tokens[11]);
          if (StringUtils.isNotBlank(unquoted)) {
            operatorName = unquoted;
          }
        }
        serviceDomain = Integer.valueOf(tokens[12]);
        activeBand = Integer.valueOf(tokens[13]);
        return;
      }
      if (tokensLength == 19 || tokensLength == 22 || tokensLength == 25 || tokensLength == 28 || tokensLength == 31 || tokensLength == 34 || tokensLength == 37) {
        // WCDMA network
        if (StringUtils.isNotBlank(tokens[0])) {
          plmn = Util.removeQuotes(StringUtils.trim(tokens[0]));
        }
        if (StringUtils.isNotBlank(tokens[1])) {
          uarfcn = Integer.valueOf(tokens[1]);
        }
        if (StringUtils.isNotBlank(tokens[2])) {
          psc = Float.valueOf(tokens[2]);
        }
        if (StringUtils.isNotBlank(tokens[3])) {
          ecio = Float.valueOf(tokens[3]);
        }
        if (StringUtils.isNotBlank(tokens[4])) {
          rscp = Integer.valueOf(tokens[4]);
        }
        if (StringUtils.isNotBlank(tokens[5])) {
          rssi = Integer.valueOf(tokens[5]);
        }
        if (StringUtils.isNotBlank(tokens[6])) {
          locationAreaCode = tokens[6];
        }
        if (StringUtils.isNotBlank(tokens[7])) {
          routingAreaCode = tokens[7];
        }
        if (StringUtils.isNotBlank(tokens[8])) {
          txPower = Integer.valueOf(tokens[8]);
        }
        if (StringUtils.isNotBlank(tokens[9])) {
          drx = Integer.valueOf(tokens[9]);
        }
        if (StringUtils.isNotBlank(tokens[10])) {
          mobilityManagementState = Integer.valueOf(tokens[10]);
        }
        if (StringUtils.isNotBlank(tokens[11])) {
          radioResourceState = Integer.valueOf(tokens[11]);
        }
        if (StringUtils.isNotBlank(tokens[12])) {
          networkOperatorMode = Integer.valueOf(tokens[12]);
        }
        if (StringUtils.isNotBlank(tokens[13])) {
          blockErrorRate = Integer.valueOf(tokens[13]);
        }
        if (StringUtils.isNotBlank(tokens[14])) {
          cellId = tokens[14];
        }
        imsi = Util.removeQuotes(tokens[15]);
        if (StringUtils.isNotBlank(tokens[16])) {
          String unquoted = Util.removeQuotes(tokens[16]);
          if (StringUtils.isNotBlank(unquoted)) {
            operatorName = unquoted;
          }
        }
        if (StringUtils.isNotBlank(tokens[17])) {
          serviceDomain = Integer.valueOf(tokens[17]);
        }
        if (StringUtils.isNotBlank(tokens[18])) {
          numberOfActiveSet = Integer.parseInt(tokens[18]);
        }
        for (int i = 0; i < numberOfActiveSet; i++) {
          if (StringUtils.isNotBlank(tokens[19 + (i * 3)]) && StringUtils.isNotBlank(tokens[20 + (i * 3)]) && StringUtils.isNotBlank(tokens[21 + (i * 3)])) {
            int p1 = Integer.parseInt(tokens[19 + (i * 3)]);
            float p2 = Float.parseFloat(tokens[20 + (i * 3)]);
            float p3 = Float.parseFloat(tokens[21 + (i * 3)]);
            activeSets.add(new ActiveSet(p1, p2, p3));
          } else {
            throw new ParseException("Could not retrieve active set " + i + " from " + line);
          }
        }
        return;
      }

      // (GSM network)  #RFSTS:<PLMN>,<ARFCN>,<RSSI>,<LAC>,<RAC>,<TXPWR>,<MM>, <RR>,<NOM>,<CID>,<IMSI>,<NetNameAsc>,<SD>,<ABND>
      // (WDMA network) #RFSTS:<PLMN>,[<UARFCN>],[<PSC>],[<Ec/Io>],[<RSCP>], [RSSI>],[<LAC>], [<RAC>],<TXPWR>,<DRX>,<MM>,<RRC>,<NOM>,<BLER>,<CID>,<IMSI>, <NetNameAsc>,<SD>,<nAST>[,<nUARFCN><nPSC>,<nEc/Io>]

      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        if (StringUtils.isNotBlank(m.group(1))) {
          plmn = m.group(1);
        }
        if (StringUtils.isNotBlank(m.group(2))) {
          arfcn = Integer.valueOf(m.group(2));
        }
        if (StringUtils.isNotBlank(m.group(3))) {
          rssi = Integer.valueOf(m.group(3));
        }
        if (StringUtils.isNotBlank(m.group(4))) {
          locationAreaCode = m.group(4);
        }
        if (StringUtils.isNotBlank(m.group(5))) {
          routingAreaCode = m.group(5);
        }
        if (StringUtils.isNotBlank(m.group(6))) {
          txPower = Integer.valueOf(m.group(6));
        }
        if (StringUtils.isNotBlank(m.group(7))) {
          mobilityManagementState = Integer.valueOf(m.group(7));
        }
        if (StringUtils.isNotBlank(m.group(8))) {
          radioResourceState = Integer.valueOf(m.group(8));
        }
        if (StringUtils.isNotBlank(m.group(9))) {
          networkOperatorMode = Integer.valueOf(m.group(9));
        }
        if (StringUtils.isNotBlank(m.group(10))) {
          cellId = m.group(10);
        }
        if (StringUtils.isNotBlank(m.group(11))) {
          imsi = m.group(11);
        }
        if (StringUtils.isNotBlank(m.group(12))) {
          operatorName = m.group(12);
        }
        if (StringUtils.isNotBlank(m.group(13))) {
          serviceDomain = Integer.valueOf(m.group(13));
        }
        if (StringUtils.isNotBlank(m.group(14))) {
          activeBand = Integer.valueOf(m.group(14));
        }
        return;
      }

      final Matcher m2 = PATTERN2.matcher(line);
      if (m2.find()) {
        if (StringUtils.isNotBlank(m2.group(1))) {
          plmn = m2.group(1);
        }
        if (StringUtils.isNotBlank(m2.group(2))) {
          arfcn = Integer.parseInt(m2.group(2));
        }
        if (StringUtils.isNotBlank(m2.group(3))) {
          rssi = Integer.parseInt(m2.group(3));
        }
        if (StringUtils.isNotBlank(m2.group(4))) {
          locationAreaCode = m2.group(4);
        }
        if (StringUtils.isNotBlank(m2.group(5))) {
          routingAreaCode = m2.group(5);
        }
        if (StringUtils.isNotBlank(m2.group(6))) {
          txPower = Integer.parseInt(m2.group(6));
        }
        if (StringUtils.isNotBlank(m2.group(7))) {
          mobilityManagementState = Integer.parseInt(m2.group(7));
        }
        if (StringUtils.isNotBlank(m2.group(8))) {
          radioResourceState = Integer.parseInt(m2.group(8));
        }
        if (StringUtils.isNotBlank(m2.group(9))) {
          networkOperatorMode = Integer.parseInt(m2.group(9));
        }
        if (StringUtils.isNotBlank(m2.group(10))) {
          cellId = m2.group(10);
        }
        if (StringUtils.isNotBlank(m2.group(11))) {
          imsi = m2.group(11);
        }
        if (StringUtils.isNotBlank(m2.group(12))) {
          operatorName = m2.group(12);
        }
        if (StringUtils.isNotBlank(m2.group(13))) {
          serviceDomain = Integer.parseInt(m2.group(13));
        }
        if (StringUtils.isNotBlank(m2.group(14))) {
          activeBand = Integer.parseInt(m2.group(14));
        }
        return;
      }

      throw createParseException(line);
    } else {
      throw createParseException(response);
    }
  }

  public String getPlmn() {
    return plmn;
  }

  public Integer getArfcn() {
    return arfcn;
  }

  public Integer getRssi() {
    return rssi;
  }

  public String getLocationAreaCode() {
    return locationAreaCode;
  }

  public String getRoutingAreaCode() {
    return routingAreaCode;
  }

  public Integer getTxPower() {
    return txPower;
  }

  public Integer getMobilityManagementState() {
    return mobilityManagementState;
  }

  public Integer getRadioResourceState() {
    return radioResourceState;
  }

  public Integer getNetworkOperatorMode() {
    return networkOperatorMode;
  }

  public String getCellId() {
    return cellId;
  }

  public String getImsi() {
    return imsi;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public Integer getServiceDomain() {
    return serviceDomain;
  }

  public Integer getActiveBand() {
    return activeBand;
  }

  public Integer getUarfcn() {
    return uarfcn;
  }

  public Float getPsc() {
    return psc;
  }

  public Float getEcio() {
    return ecio;
  }

  public Integer getRscp() {
    return rscp;
  }

  public Integer getDrx() {
    return drx;
  }

  public Integer getBlockErrorRate() {
    return blockErrorRate;
  }

  public Integer getNumberOfActiveSet() {
    return numberOfActiveSet;
  }

  public List<ActiveSet> getActiveSets() {
    return activeSets;
  }

  @Override
  public String toString() {
    return line;
  }
}
