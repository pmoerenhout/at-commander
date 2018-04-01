package com.github.pmoerenhout.atcommander.common;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StrTokenizer;

public class Splitter {

  private String[] values;
  private int length;

  public Splitter(final String str) {
      values = parse(str);
      length = values.length;
  }

  public Splitter(final String str, final String prefix) {
    final String stripped = StringUtils.stripStart(str, prefix);
    values = parse(stripped);
    length = values.length;
  }

  private String[] parse(final String str) {
    // final String data = StringUtils.stripStart(str, stripChars);
    final StrTokenizer tokenizer = new StrTokenizer(str).setDelimiterChar(',').setQuoteChar('\"')
        .setIgnoreEmptyTokens(false).setEmptyTokenAsNull(false);
    final String[] tokenArray = tokenizer.getTokenArray();
    length = tokenArray.length;
    if (length == 0) {
      throw new IllegalArgumentException("Could not parse: '" + str + "'");
    }
    return tokenArray;
  }

  public int getLength() {
    return length;
  }

  public String getString(final int index){
    if (index < length){
      return values[index];
    }
    return null;
  }

  public Integer getInteger(final int index){
    if (index < length){
      return Integer.parseInt(values[index]);
    }
    return null;
  }

}
