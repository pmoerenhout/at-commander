package org.atcommander.module.telit.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.atcommander.basic.commands.Response;
import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseResponse;

public class ServiceProviderNameResponse extends BaseResponse implements Response {

  // #SPN: Aspider
  private static final Pattern PATTERN = Pattern.compile("^\\#SPN: (.*)$");

  private String serviceProviderName;

  public ServiceProviderNameResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 0) {
      // null response in case of no SPN
      return;
    }
    else if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        serviceProviderName = m.group(1);
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public String getServiceProviderName() {
    return serviceProviderName;
  }
}
