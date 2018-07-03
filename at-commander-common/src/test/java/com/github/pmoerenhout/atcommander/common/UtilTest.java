package com.github.pmoerenhout.atcommander.common;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class UtilTest {

  @Test
  public void test_integer_values_single() {
    final List<Integer> values = Util.toIntegerValues("55");
    assertTrue(values.contains(new Integer(55)));
  }

  @Test
  public void test_integer_values_range() {
    final List<Integer> values = Util.toIntegerValues("1-3");
    assertTrue(values.contains(new Integer(1)));
    assertTrue(values.contains(new Integer(2)));
    assertTrue(values.contains(new Integer(3)));
  }

  @Test
  public void test_integer_values_mixed() {
    final List<Integer> values = Util.toIntegerValues("2-5,8");
    assertTrue(values.contains(new Integer(2)));
    assertTrue(values.contains(new Integer(3)));
    assertTrue(values.contains(new Integer(4)));
    assertTrue(values.contains(new Integer(5)));
    assertTrue(values.contains(new Integer(8)));
  }
}