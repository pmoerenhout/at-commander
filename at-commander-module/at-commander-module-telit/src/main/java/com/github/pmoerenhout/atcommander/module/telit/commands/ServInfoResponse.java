package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StrTokenizer;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.basic.exceptions.ParseException;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;

public class ServInfoResponse extends BaseResponse implements Response {

  // #SERVINFO: 1020,-55,"vodafone NL","20404",61,00DE,00,0
  // #SERVINFO: ,,"vodafone NL","20404",,00DE,64,1,,"II"
  // #SERVINFO: 3011,-79,"NL KPN","20408",90,00DE,64,1,-87
  // #SERVINFO: 1020,-61,"vodafone NL","20404",61,00DE,00,1,,"II",01,6

  private static final String SERVINFO = "#SERVINFO: ";

  private String line;

  private Integer bcchArfcn;
  private Integer dbm;
  private String netName;
  private String netCode;
  private String baseStationIdentificationCode;
  private String locationAreaCode;
  private String timeAdvance;
  private Boolean gprsSupported;
  private String pbArfcn;
  private String networkOperationMode;
  private String routingAreaColourCode;
  private Integer priorityAccessThreshold;
  private Integer umtsArfcn;
  private String primarySynchronisationCode;
  private String discontinuousReceptionCycleLength;
  private String serviceDomain;
  private Integer receivedSignalCodePower;

  private AccessTechnology accessTechnology;

  public ServInfoResponse(final AtResponse s, final AccessTechnology accessTechnology) {
    this.accessTechnology = accessTechnology;
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    if (this.accessTechnology == null) {
      throw new IllegalArgumentException("No Access Technology given for " + line);
    }
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      line = informationalText.get(0);
      final String data = StringUtils.stripStart(line, SERVINFO);
      final StrTokenizer tokenizer = new StrTokenizer(data).setDelimiterChar(',').setQuoteChar('\"')
          .setIgnoreEmptyTokens(false).setEmptyTokenAsNull(true);

      final String[] tokenArray = tokenizer.getTokenArray();
      // LOG.debug("Data: {} Tokens: {} ACT:{}", data, tokenArray.length, accessTechnology);
      final int length = tokenArray.length;
      if (length == 0) {
        throw new ParseException("Could not parse response: " + line);
      }
      switch (this.accessTechnology) {
        case GSM:
          bcchArfcn = getTokenAsInteger(tokenArray, 0);
          dbm = getTokenAsInteger(tokenArray, 1);
          netName = getToken(tokenArray, 2);
          netCode = getToken(tokenArray, 3);
          baseStationIdentificationCode = getToken(tokenArray, 4);
          locationAreaCode = getToken(tokenArray, 5);
          timeAdvance = getToken(tokenArray, 6);
          gprsSupported = getTokenAsBoolean(tokenArray, 7);
          pbArfcn = getToken(tokenArray, 8);
          networkOperationMode = getToken(tokenArray, 9);
          routingAreaColourCode = getToken(tokenArray, 10);
          priorityAccessThreshold = getTokenAsInteger(tokenArray, 11);
          break;

        case UTRAN:
          umtsArfcn = getTokenAsInteger(tokenArray, 0);
          dbm = getTokenAsInteger(tokenArray, 1);
          netName = getToken(tokenArray, 2);
          netCode = getToken(tokenArray, 3);
          primarySynchronisationCode = getToken(tokenArray, 4);
          locationAreaCode = getToken(tokenArray, 5);
          discontinuousReceptionCycleLength = getToken(tokenArray, 6);
          serviceDomain = getToken(tokenArray, 7);
          receivedSignalCodePower = getTokenAsInteger(tokenArray, 8);
          networkOperationMode = getToken(tokenArray, 9);
          routingAreaColourCode = getToken(tokenArray, 10);
          break;
        default:
          throw new ParseException("Unknown access technology");
      }
    } else {
      throw createParseException(response);
    }
  }

  public Integer getBcchArfcn() {
    return bcchArfcn;
  }

  public Integer getDbm() {
    return dbm;
  }

  public String getNetName() {
    return netName;
  }

  public String getNetCode() {
    return netCode;
  }

  public String getBaseStationIdentificationCode() {
    return baseStationIdentificationCode;
  }

  public String getLocationAreaCode() {
    return locationAreaCode;
  }

  public String getTimeAdvance() {
    return timeAdvance;
  }

  public Boolean isGprsSupported() {
    return gprsSupported;
  }

  public String getPbArfcn() {
    return pbArfcn;
  }

  public String getNetworkOperationMode() {
    return networkOperationMode;
  }

  public String getRoutingAreaColourCode() {
    return routingAreaColourCode;
  }

  public Integer getPriorityAccessThreshold() {
    return priorityAccessThreshold;
  }

  public Integer getUmtsArfcn() {
    return umtsArfcn;
  }

  public String getPrimarySynchronisationCode() {
    return primarySynchronisationCode;
  }

  public String getDiscontinuousReceptionCycleLength() {
    return discontinuousReceptionCycleLength;
  }

  public String getServiceDomain() {
    return serviceDomain;
  }

  public Integer getReceivedSignalCodePower() {
    return receivedSignalCodePower;
  }

  @Override
  public String toString() {
    return line;
  }
}
