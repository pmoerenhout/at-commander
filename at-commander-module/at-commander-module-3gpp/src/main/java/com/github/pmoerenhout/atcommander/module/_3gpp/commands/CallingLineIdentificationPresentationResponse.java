package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class CallingLineIdentificationPresentationResponse extends BaseResponse implements Response {

  // +CLIP: 0,1
  private static final Pattern PATTERN = Pattern.compile("^\\+CLIP: (\\d),(\\d)");

  private Boolean callingLineIndicationPresentation;
  private Boolean callingLineIndicationPresentationProvisioned;

  public CallingLineIdentificationPresentationResponse() {
  }

  public CallingLineIdentificationPresentationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        callingLineIndicationPresentation = BooleanUtils.toBooleanObject(Integer.parseInt(m.group(1)));
        callingLineIndicationPresentationProvisioned = BooleanUtils.toBooleanObject(Integer.parseInt(m.group(2)));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public Boolean getCallingLineIndicationPresentation() {
    return callingLineIndicationPresentation;
  }

  public Boolean getCallingLineIndicationPresentationProvisioned() {
    return callingLineIndicationPresentationProvisioned;
  }
}