package com.github.pmoerenhout.atcommander.module.v250.commands;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.BasicFinalFactory;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import junit.framework.Assert;

public class TestResponseTest extends BaseCommandTest {

  @Test
  public void test() {
    // +CGCLASS: ("A","B","CG","CC")
    AtResponse response = createOkAtResponse("+CGCLASS: (\"A\",\"B\",\"CG\",\"CC\")");
    TestResponse testResponse = new TestResponse(response);
    Assert.assertEquals("A", testResponse.getValues().get(0));
  }

  protected AtResponse createOkAtResponse(final List<String> lines) {
    return new AtResponse(Collections.singletonList(new BasicFinalFactory()), lines);
  }
}