package org.atcommander.module.v250.commands;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;
import org.atcommander.module.v250.DialFinalResponse;
import org.atcommander.module.v250.types.DialStatus;

public class DialResponse extends BaseResponse {

  private DialStatus status;
  private String text;

  public DialResponse(final AtResponse response) {
    if (response.getFinalResponse2() instanceof DialFinalResponse) {
      final DialFinalResponse dialFinalResponse = ((DialFinalResponse) response.getFinalResponse2());
      status = dialFinalResponse.getStatus();
      text = dialFinalResponse.getText();
    }
    //response.getFinalResponse2().throwIfNeccessary();
    // parse(response);
//    switch (response.getFinalResponse2()){
//      case :
//        status = DialStatus.NO_CARRIER;
//        break;
//      case CONNECTED:
//        status = DialStatus.CONNECTED;
//        break;
//      default:
//        throw new ParseException("The dial response could not be recognized (" + response.getFinalResponseCode() + ")");
//    }
  }

//  public void parse(final AtResponse response) {
//    final List<String> informationalText = response.getInformationalText();
//    if (informationalText.size() == 1) {
//      final String line = informationalText.get(0);
//      final Matcher m1 = PATTERN_NO_CARRIER.matcher(line);
//      if (m1.find()) {
//        this.status = DialStatus.NO_CARRIER;
//        return;
//      }
//      final Matcher m2 = PATTERN_NO_DIALTONE.matcher(line);
//      if (m2.find()) {
//        this.status = DialStatus.NO_DIALTONE;
//        return;
//      }
//      final Matcher m3 = PATTERN_BUSY.matcher(line);
//      if (m3.find()) {
//        this.status = DialStatus.BUSY;
//        return;
//      }
//      final Matcher m4 = PATTERN_CONNECTED.matcher(line);
//      if (m4.find()) {
//        this.status = DialStatus.CONNECT;
//        return;
//      }
//      throw createParseException(line);
//    }
//    throw createParseException(response);
//  }

  public DialStatus getStatus() {
    return status;
  }
  public String getText() {
    return text;
  }
}

