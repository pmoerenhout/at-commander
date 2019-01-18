package com.github.pmoerenhout.atcommander.common;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringTokenizer;

public class Util {

  private final static char SEPARATOR_CHAR = ',';
  private final static char QUOTE_CHAR = '"';
  private final static char PARENTHESIS_START_CHAR = '(';
  private final static char PARENTHESIS_END_CHAR = ')';

  public static String onlyPrintable(final byte[] buf) {
    final int l = buf.length;
    final StringBuilder sb = new StringBuilder(l);
    for (int i = 0; i < l; i++) {
      final byte b = buf[i];
      if (b >= 32 && b < 127) {
        sb.append((char) b);
      } else {
        sb.append(String.format("<%02x>", b));
      }
    }
    return sb.toString();
  }

  public static String removeQuotes(final String str) {
    final int length = str.length();
    if (length > 1) {
      if (str.charAt(0) == '\"' && str.charAt(length - 1) == '\"') {
        return str.substring(1, length - 1);
      }
    }
    return str;
  }

  static public String bytesToHexString(final byte[] bytes) {
    final StringBuilder sb = new StringBuilder(bytes.length * 2);
    final Formatter formatter = new Formatter(sb);
    for (byte b : bytes) {
      formatter.format("%02X", b);
    }
    return sb.toString();
  }

  public static byte[] hexToByteArray(final String s) {
    final String s2 = s.replaceAll(" ", "");
    int length = s2.length() / 2;
    byte[] b = new byte[length];
    for (int i = 0; i < length; i++) {
      b[i] = Integer.valueOf(s2.substring(i * 2, (i * 2) + 2), 16).byteValue();
    }
    return b;
  }

  public static byte hexToByte(final String s) {
    final String s2 = s.replaceAll(" ", "");
    int length = s2.length() / 2;
    final byte b = Integer.valueOf(s2.substring(0, 2), 16).byteValue();
    return b;
  }

  public static String[] tokenize(final String str) {
    return tokenize(str, SEPARATOR_CHAR, QUOTE_CHAR);
  }

  public static String[] tokenize(final String str, final char delimiterChar, final char quoteChar) {
    final StringTokenizer tokenizer = new StringTokenizer(str).setDelimiterChar(delimiterChar).setQuoteChar(quoteChar)
        .setIgnoreEmptyTokens(false).setEmptyTokenAsNull(true);
    return tokenizer.getTokenArray();
  }

  static public String[] splitAndRemoveParenthesis(final String str) {
    return split(str, SEPARATOR_CHAR, PARENTHESIS_START_CHAR, PARENTHESIS_END_CHAR);
  }

  static public String[] split(final String str, final char separatorChar, final char blockStartChar, final char endStartChar) {
    if (str == null) {
      return null;
    }
    final int len = str.length();
    if (len == 0) {
      return ArrayUtils.EMPTY_STRING_ARRAY;
    }
    final List<String> list = new ArrayList<>();
    int i = 0, start = 0;
    int blockLevel = 0;
    boolean match = false;
    boolean lastMatch = false;
    while (i < len) {
      if (str.charAt(i) == blockStartChar) {
        blockLevel++;
      }
      if (str.charAt(i) == endStartChar) {
        blockLevel--;
      }
      if (blockLevel == 0) {
        if (str.charAt(i) == separatorChar) {
          if (match || true) {
            list.add(removeParenthesis(str.substring(start, i)));
            match = false;
            lastMatch = true;
          }
          start = ++i;
          continue;
        }
      }
      lastMatch = false;
      match = true;
      i++;
    }
    if (match || true && lastMatch) {
      list.add(removeParenthesis(str.substring(start, i)));
    }
    if (blockLevel != 0) {
      throw new IllegalStateException("The text contains non-matching parenthesis");
    }
    return list.toArray(new String[list.size()]);
  }

  public static String removeParenthesis(final String str) {
    return removeParenthesis(str, PARENTHESIS_START_CHAR, PARENTHESIS_END_CHAR);
  }

  public static String removeParenthesis(final String str, final char startChar, final char endChar) {
    if (str != null) {
      final int length = str.length();
      if (length > 1) {
        if (str.charAt(0) == startChar && str.charAt(length - 1) == endChar) {
          return str.substring(1, length - 1);
        }
      }
    }
    return str;
  }

  public static List<Integer> toIntegerValues(final String text) {
    // value 1-3, 6
    final List<Integer> values = new ArrayList<>();
    final String[] splitted = StringUtils.split(text, ',');
    for (final String item : splitted) {
      if (item.contains("-")) {
        final int min = Integer.parseInt(StringUtils.substringBefore(item, "-"));
        final int max = Integer.parseInt(StringUtils.substringAfter(item, "-"));
        IntStream.rangeClosed(min, max).forEach(i -> values.add(i));
      } else {
        values.add(Integer.valueOf(item));
      }
    }
    return values;
  }

  public static ZonedDateTime getTimestamp(final String datetime)
  {
    // timestamp is formatted as 18/09/28,17:02:36+08
    int year = Integer.parseInt(StringUtils.substring(datetime,0,2));
    int month = Integer.parseInt(StringUtils.substring(datetime,3,5));
    int day = Integer.parseInt(StringUtils.substring(datetime,6,8));
    int hour = Integer.parseInt(StringUtils.substring(datetime,9,11));
    int minute = Integer.parseInt(StringUtils.substring(datetime,12,14));
    int second = Integer.parseInt(StringUtils.substring(datetime,15,17));
    int timezoneQuarters = Integer.parseInt(StringUtils.substring(datetime,17,20));
    final ZoneOffset zoneOffset = ZoneOffset.ofTotalSeconds(timezoneQuarters * 900);
    return ZonedDateTime.of(year + 2000, month, day, hour, minute, second, 0, zoneOffset);
  }

}
