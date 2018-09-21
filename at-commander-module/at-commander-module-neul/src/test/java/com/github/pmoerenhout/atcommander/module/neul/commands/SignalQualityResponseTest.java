package com.github.pmoerenhout.atcommander.module.neul.commands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.BasicFinalFactory;

public class SignalQualityResponseTest {

  @Test
  public void test_signal_quality_response() throws Exception {
    final AtResponse response = createOkAtResponse("+CSQ: 2,99");

    final SignalQualityResponse signalQuality = new SignalQualityResponse(response);

    assertEquals(2, signalQuality.getRssi());
    assertEquals(99, signalQuality.getBer());
  }

  protected AtResponse createOkAtResponse(final String line) {
    final List<String> lines = new ArrayList<>();
    lines.add(line);
    lines.add("OK");
    return new AtResponse(Collections.singletonList(new BasicFinalFactory()), lines);
  }
}