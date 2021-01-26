package com.github.pmoerenhout.atcommander.module.quectel.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class QueryNetworkInformationResponse extends BaseResponse implements Response {

  // +QNWINFO: <Act>,<oper>,<band>,<channel>
  // +QNWINFO: "FDD LTE","20408","LTE BAND 20",1300
  private static final Pattern PATTERN = Pattern.compile("^\\+QNWINFO: \"(.*)\",\"(.*)\",\"(.*)\",(\\d*)?$");

  private String accessTechnology;
  private String operator;
  private String band;
  private Integer channel;

  public QueryNetworkInformationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        accessTechnology = m.group(1);
        operator = m.group(2);
        band = m.group(3);
        channel = Integer.parseInt(m.group(4));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getAccessTechnology() {
    return accessTechnology;
  }

  public String getOperator() {
    return operator;
  }

  public String getBand() {
    return band;
  }

  public Integer getChannel() {
    return channel;
  }
}
