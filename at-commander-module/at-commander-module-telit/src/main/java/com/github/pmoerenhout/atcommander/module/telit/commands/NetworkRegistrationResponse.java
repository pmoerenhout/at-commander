//package com.github.pmoerenhout.atcommander.module.telit.commands;
//
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import com.github.pmoerenhout.atcommander.AtResponse;
//import com.github.pmoerenhout.atcommander.basic.commands.Response;
//import com.github.pmoerenhout.atcommander.module._3gpp.RegistrationState;
//import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;
//
//public class NetworkRegistrationResponse extends com.github.pmoerenhout.atcommander.module._3gpp.commands.NetworkRegistrationResponse
//    implements Response {
//
////   public static final Pattern UNSOLICITED_PATTERN = Pattern.compile("^\\+CREG: (\\d),\"(.*)\",\"(.*)\",(\\d)$");
//
//  private static final Pattern PATTERN = Pattern.compile("^\\+CREG: (\\d),(\\d),\"(.*)\",\"(.*)\",(\\d)$");
//
//  private AccessTechnology accessTechnology;
//
//  public NetworkRegistrationResponse() {
//  }
//
//  public NetworkRegistrationResponse(final AtResponse response) {
//    parseSolicited(response);
//  }
//
//  public void parseSolicited(final AtResponse response) {
//    final List<String> informationalText = response.getInformationalText();
//    if (informationalText.size() == 1) {
//      final String line = informationalText.get(0);
//      final Matcher m5 = PATTERN.matcher(line);
//      if (m5.find()) {
//        mode = Integer.parseInt(m5.group(1));
//        registrationState = RegistrationState.fromString(m5.group(2));
//        lac = Integer.parseUnsignedInt(m5.group(3), 16);
//        cellId = Integer.parseUnsignedInt(m5.group(4), 16);
//        accessTechnology = AccessTechnology.fromInt(Integer.parseInt(m5.group(5)));
//        return;
//      }
//      super.parseSolicited(response);
//    } else {
//      throw createParseException(response);
//    }
//  }
//
//  public AccessTechnology getAccessTechnology() {
//    return accessTechnology;
//  }
//}