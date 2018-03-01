package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import org.junit.Test;

public class SendMessageResponseTest extends BaseCommandTest {

  @Test
  public void testSendSmsResponse() {
    final AtResponse response = createOkAtResponse("+CMGS: 55");

    final SendMessageResponse sendMessageResponse = new SendMessageResponse(response);

    assertEquals(55, sendMessageResponse.getReference());
  }

  @Test
  public void test_join() {
    final String[] line = new String[]{ "+CMGS: 55", "ddd" };
    final StringJoiner stringJoiner = new StringJoiner(",");

    List<String> numbers = Arrays.asList(line);
    String commaSeparatedNumbers = numbers.stream()
        .map(i -> "\"" + i + "\"")
        .collect(Collectors.joining(", ", "\"", "\""));

    System.out.println(commaSeparatedNumbers);
  }
}