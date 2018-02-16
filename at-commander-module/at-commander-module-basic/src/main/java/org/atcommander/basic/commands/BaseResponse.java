package org.atcommander.basic.commands;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.atcommander.AtResponse;
import org.atcommander.basic.exceptions.ParseException;

public abstract class BaseResponse {

  protected static final char COMMA = ',';

  protected String getToken(final String[] tokenArray, final int index) {
    if (tokenArray.length > index) {
      if (tokenArray[index] != null) {
        return tokenArray[index];
      }
    }
    return null;
  }

  protected Integer getTokenAsInteger(final String[] tokenArray, final int index) {
    if (tokenArray.length > index) {
      if (tokenArray[index] != null) {
        return Integer.parseInt(tokenArray[index]);
      }
    }
    return null;
  }

  protected Boolean getTokenAsBoolean(final String[] tokenArray, final int index) {
    if (tokenArray.length > index) {
      if (tokenArray[index] != null) {
        return BooleanUtils.toBoolean(Integer.parseInt(tokenArray[index]));
      }
    }
    return null;
  }

  protected String trimDoubleQuotes(final String text) {
    final int textLength = text.length();
    if (textLength >= 2 && text.charAt(0) == '"' && text.charAt(textLength - 1) == '"') {
      return text.substring(1, textLength - 1);
    }
    return text;
  }

  protected ParseException createParseException(final String line) {
    return new ParseException("The response could not be parsed (" + line + ") [" + this.getClass().getName() + "]");
  }

  protected ParseException createParseException(final List<String> response) {
    final String commaSeparatedItems = Arrays.asList(response).stream()
        .map(s -> "\"" + s + "\"")
        .collect(Collectors.joining(", ", "(", ")"));
    return new ParseException(
        "The response has incorrect number of " + response.size() + " lines " + commaSeparatedItems + " [" + this.getClass().getName() + "]");
  }

  protected ParseException createParseException(final AtResponse response) {
    final List<String> informationalText = response.getInformationalText();
    final String commaSeparatedItems = Arrays.asList(informationalText).stream()
        .map(s -> "\"" + s + "\"")
        .collect(Collectors.joining(", ", "(", ")"));
    return new ParseException(
        "The response has incorrect number of " + informationalText.size() + " lines " + commaSeparatedItems + " [" + this.getClass().getName() + "]");
  }

  protected ParseException createParseException(final String[] response) {
    final String commaSeparatedItems = Arrays.asList(response).stream()
        .map(s -> "\"" + s + "\"")
        .collect(Collectors.joining(", ", "(", ")"));
    return new ParseException(
        "The response has incorrect number of " + response.length + " lines " + commaSeparatedItems + " [" + this.getClass().getName() + "]");
  }

}
