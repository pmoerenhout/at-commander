package com.github.pmoerenhout.atcommander.common;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class UtilTest {

  @Test
  public void test_integer_values_single() {
    final List<Integer> values = Util.toIntegerValues("55");
    assertTrue(values.contains(Integer.valueOf(55)));
  }

  @Test
  public void test_integer_values_range() {
    final List<Integer> values = Util.toIntegerValues("1-3");
    assertTrue(values.contains(Integer.valueOf(1)));
    assertTrue(values.contains(Integer.valueOf(2)));
    assertTrue(values.contains(Integer.valueOf(3)));
  }

  @Test
  public void test_integer_values_mixed() {
    final List<Integer> values = Util.toIntegerValues("2-5,8");
    assertTrue(values.contains(Integer.valueOf(2)));
    assertTrue(values.contains(Integer.valueOf(3)));
    assertTrue(values.contains(Integer.valueOf(4)));
    assertTrue(values.contains(Integer.valueOf(5)));
    assertTrue(values.contains(Integer.valueOf(8)));
  }

  @Test
  public void test_join() {
    final String[] linesArray = new String[]{ "+CMGS: 55", "ddd" };
    // final StringJoiner stringJoiner = new StringJoiner(",");
    List<String> lines = Arrays.asList(linesArray);
    String commaSeparatedNumbers = lines.stream()
        .map(i -> "\"" + i + "\"")
        .collect(Collectors.joining(", ", "\"", "\""));

    System.out.println(commaSeparatedNumbers);
  }
}