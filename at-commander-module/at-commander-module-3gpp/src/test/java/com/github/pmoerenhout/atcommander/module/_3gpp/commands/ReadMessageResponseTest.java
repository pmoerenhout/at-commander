package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.types.PduMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.TextMessage;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageMode;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class ReadMessageResponseTest extends BaseCommandTest {

  @Test
  public void test_cmgr_text_rec_unread_with_csdh_set() throws Exception {

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

  @Test
  public void test_cmgr_text_rec_unread() throws Exception {
    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGR: \"REC UNREAD\",\"+31614240689\",\"\",\"18/10/05,12:16:11+08\"",
            "Europe wins the Ryder cup again",
            "OK"
        });

    final ReadMessageResponse readMessageResponse = new ReadMessageResponse(MessageMode.TEXT, response);

    final TextMessage message = (TextMessage) readMessageResponse.getMessage();
    assertEquals(MessageStatus.RECEIVED_UNREAD, message.getStatus());
    assertEquals("+31614240689", message.getOada());
    assertEquals("", message.getAlpha());
    assertEquals(ZonedDateTime.of(2018, 10, 5, 12, 16, 11, 0, ZoneId.of("+02")),
        message.getScts());
    assertNull(message.getToOa());
    assertNull(message.getFirstOctet());
    assertNull(message.getPid());
    assertNull(message.getDcs());
    assertNull(message.getSca());
    assertNull(message.getToSca());
    assertNull(message.getLength());
    assertEquals("Europe wins the Ryder cup again", message.getText());
  }

  @Test
  public void test_cmgr_pdu() throws Exception {
    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGR: 0,,41",
            "099193338548000011F6040D91137910248665F60004025021113150001508411181BC3048320035A001AD800D1454EDFD95C0",
            "OK"
        });

    final ReadMessageResponse readMessageResponse = new ReadMessageResponse(MessageMode.PDU, response);

    final PduMessage message = (PduMessage) readMessageResponse.getMessage();
    assertEquals(MessageStatus.RECEIVED_UNREAD, message.getStatus());
    assertEquals("099193338548000011F6040D91137910248665F60004025021113150001508411181BC3048320035A001AD800D1454EDFD95C0", message.getPdu());
    assertNull(message.getAlpha());
    assertEquals(41, message.getLength());
    assertEquals("099193338548000011F6040D91137910248665F60004025021113150001508411181BC3048320035A001AD800D1454EDFD95C0", message.getPdu());
  }
}