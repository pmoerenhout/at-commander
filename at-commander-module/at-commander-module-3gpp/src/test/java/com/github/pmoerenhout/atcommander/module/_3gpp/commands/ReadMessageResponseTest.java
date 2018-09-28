package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.types.TextMessage;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class ReadMessageResponseTest extends BaseCommandTest {

  @Test
  public void test_cmgr_text_rec_unread() throws Exception {

    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGR: \"REC UNREAD\",\"+31612345678\",\"\",\"18/09/28,16:34:55+08\",145,4,0,0,\"+31640191919\",145,25",
            "Europe wins the Ryder cup",
            "OK"
        });

    final ReadMessageResponse readMessageResponse = new ReadMessageResponse(MessageMode.TEXT, response);

    final TextMessage message = (TextMessage) readMessageResponse.getMessage();
    assertEquals(MessageStatus.RECEIVED_UNREAD, message.getStatus());
    assertEquals("+31612345678", message.getOada());
    assertEquals("", message.getAlpha());
    assertEquals(ZonedDateTime.of(2018, 9, 28, 16, 34, 55, 0, ZoneId.of("+02")),
        message.getScts());
    assertEquals("Europe wins the Ryder cup", message.getText());
  }
}