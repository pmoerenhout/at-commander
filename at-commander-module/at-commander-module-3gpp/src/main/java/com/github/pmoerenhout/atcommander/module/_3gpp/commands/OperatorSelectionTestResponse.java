package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseResponse;
import com.github.pmoerenhout.atcommander.basic.commands.Response;
import com.github.pmoerenhout.atcommander.common.Util;
import com.github.pmoerenhout.atcommander.module._3gpp.types.Operator;
import com.github.pmoerenhout.atcommander.module.v250.enums.AccessTechnology;
import com.github.pmoerenhout.atcommander.module.v250.enums.Status;

public class OperatorSelectionTestResponse extends BaseResponse implements Response {

  private static final Pattern PATTERN = Pattern.compile("^\\+COPS: (.*)$");

  private List<Operator> operators = new ArrayList<>();
  private String mode;
  private String format;

  public OperatorSelectionTestResponse(final AtResponse s) {
    parse(s);
  }

  public void parse(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    if (informationalText.size() == 1) {
      final String line = informationalText.get(0);
      final Matcher m = PATTERN.matcher(line);
      if (m.find()) {
        final String[] splitted = Util.splitAndRemoveParenthesis(m.group(1));
        for (int i = 0; i < splitted.length; i++) {
          final String[] operatorItems = Util.tokenize(splitted[i]);
          if (operatorItems.length >= 4) {
            final Status status = Status.fromInt(Integer.parseInt(operatorItems[0]));
            final String operatorLong = operatorItems[1];
            final String operatorShort = operatorItems[2];
            final String operatorNumeric = operatorItems[3];
            if (operatorItems.length == 5) {
              final AccessTechnology accessTechnology = AccessTechnology.fromInt(Integer.parseInt(operatorItems[4]));
              operators.add(new Operator(status, operatorLong, operatorShort, operatorNumeric, accessTechnology));
            } else {
              operators.add(new Operator(status, operatorLong, operatorShort, operatorNumeric));
            }
          } else {
            break;
          }
        }
        mode = splitted[splitted.length - 2];
        format = splitted[splitted.length - 1];
        return;
      }
      throw createParseException(line);
    }
    throw createParseException(response);
  }

  public List<Operator> getOperators() {
    return operators;
  }

  public String getMode() {
    return mode;
  }

  public String getFormat() {
    return format;
  }
}
