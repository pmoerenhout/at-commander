package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.BooleanUtils;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;

public class ConnectedLineIdentificationPresentationResponse extends BaseResponse implements Response {

  // +CLIP: 0,1
  private static final Pattern PATTERN = Pattern.compile("^\\+COLP: (\\d),(\\d)");

  private Boolean connectedLineIndicationPresentation;
  private Integer connectedLineIndicationPresentationProvisioned;

  public ConnectedLineIdentificationPresentationResponse() {
  }

  public ConnectedLineIdentificationPresentationResponse(final AtResponse s) {
    parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        connectedLineIndicationPresentation = BooleanUtils.toBooleanObject(Integer.parseInt(m.group(1)));
        connectedLineIndicationPresentationProvisioned = Integer.parseInt(m.group(2));
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public Boolean getConnectedLineIndicationPresentation() {
    return connectedLineIndicationPresentation;
  }

  public Integer getConnectedLineIndicationPresentationProvisioned() {
    return connectedLineIndicationPresentationProvisioned;
  }
}