package com.github.pmoerenhout.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;

public class IndicatorControlResponse extends BaseResponse implements Response {

  // +CIND: 5,0,1,0,0,0,1,0,5
  private final static Pattern PATTERN = Pattern.compile("^\\+CIND: (\\d*),(\\d*),(\\d*),(\\d*),(\\d*),(\\d*),(\\d*),(\\d*),(\\d*)$");

  private int batteryChargeLevel;
  private int signalQuality;
  private int serviceAvailability;
  private int sounderActivity;
  private int messageReceived;
  private int callInProgress;
  private int roaming;
  private int smsFull;
  private int receivedSignalStrength;

  public IndicatorControlResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        batteryChargeLevel = Integer.parseInt(m.group(1));
        signalQuality = Integer.parseInt(m.group(2));
        serviceAvailability = Integer.parseInt(m.group(3));
        sounderActivity = Integer.parseInt(m.group(4));
        messageReceived = Integer.parseInt(m.group(5));
        callInProgress = Integer.parseInt(m.group(6));
        roaming = Integer.parseInt(m.group(7));
        smsFull = Integer.parseInt(m.group(8));
        receivedSignalStrength = Integer.parseInt(m.group(9));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public int getBatteryChargeLevel() {
    return batteryChargeLevel;
  }

  public int getSignalQuality() {
    return signalQuality;
  }

  public int getServiceAvailability() {
    return serviceAvailability;
  }

  public int getSounderActivity() {
    return sounderActivity;
  }

  public int getMessageReceived() {
    return messageReceived;
  }

  public int getCallInProgress() {
    return callInProgress;
  }

  public int getRoaming() {
    return roaming;
  }

  public int getSmsFull() {
    return smsFull;
  }

  public int getReceivedSignalStrength() {
    return receivedSignalStrength;
  }
}
