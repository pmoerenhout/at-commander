package com.github.pmoerenhout.atcommander.basic.commands;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.pmoerenhout.atcommander.AtResponse;

public class EmptyResponse extends BaseResponse implements Response {

  private static final Logger LOG = LoggerFactory.getLogger(EmptyResponse.class);

  public EmptyResponse(final AtResponse s) {
    this.parseSolicited(s);
  }

  public void parseSolicited(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() != 0) {
      for (final String line : informationalText) {
        LOG.info("Did not expect any response, but got '{}'", line);
      }
    }
  }
}