package com.github.pmoerenhout.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.github.pmoerenhout.atcommander.AtResponse;
import com.github.pmoerenhout.atcommander.basic.commands.BaseCommandTest;
import com.github.pmoerenhout.atcommander.module._3gpp.types.IndexMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.IndexPduMessage;
import com.github.pmoerenhout.atcommander.module._3gpp.types.IndexTextMessage;
import com.github.pmoerenhout.atcommander.module.v250.enums.MessageStatus;

public class ListMessagesResponseTest extends BaseCommandTest {

  @Ignore
  @Test
  public void test_cmgl_text_rec_read_1() throws Exception {

    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGR: \"REC UNREAD\",\"+31614240689\",\"\",\"18/09/28,16:34:55+08\",145,4,0,0,\"+31640191919\",145,33",
            "Bla Bla Bla this is the Ryder cup",
            "OK"
        });

    final ListMessagesResponse listMessagesResponse = new ListMessagesResponse(response);

    final IndexTextMessage message = (IndexTextMessage) listMessagesResponse.getIndexMessages().get(0);
    assertEquals(1, message.getIndex());
    assertEquals(MessageStatus.RECEIVED_READ, message.getStatus());
    assertEquals("+31612345678", message.getOada());
    assertEquals("", message.getAlpha());
    assertEquals(ZonedDateTime.of(2017, 12, 13, 2, 34, 52, 0, ZoneId.of("+04")),
        message.getScts());
  }

  @Test
  public void test_cmgl_text_rec_read() throws Exception {

    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGL: 1,\"REC READ\",\"+31612345678\",\"\",\"13/12/17,02:34:52+04\"",
            "Test message",
            "OK"
        });

    final ListMessagesResponse listMessagesResponse = new ListMessagesResponse(response);

    final IndexTextMessage message = (IndexTextMessage) listMessagesResponse.getIndexMessages().get(0);
    assertEquals(1, message.getIndex());
    assertEquals(MessageStatus.RECEIVED_READ, message.getStatus());
    assertEquals("+31612345678", message.getOada());
    assertEquals("", message.getAlpha());
    assertEquals(ZonedDateTime.of(2017, 12, 13, 2, 34, 52, 0, ZoneId.of("+04")),
        message.getScts());
  }

  @Test
  public void test_cmgl_pdu_rec_read_without_alpha() throws Exception {

    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGL: 1,0,27",
            "07911346101919F9040B911316240486F900007160621123918009C8309BFD0641D36D",
            "OK"
        });

    final ListMessagesResponse listMessagesResponse = new ListMessagesResponse(response);
    final List<IndexMessage> messageList = listMessagesResponse.getIndexMessages();

    assertEquals(1, messageList.size());

    final IndexPduMessage message0 = (IndexPduMessage) messageList.get(0);
    assertEquals(1, message0.getIndex());
    assertEquals(MessageStatus.RECEIVED_UNREAD, message0.getStatus());
    assertNull(message0.getAlpha());
    assertEquals("07911346101919F9040B911316240486F900007160621123918009C8309BFD0641D36D", message0.getPdu());
  }

  @Test
  public void test_cmgl_pdu_rec_read_with_alpha() throws Exception {

    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGL: 1,0,\"\",27",
            "07911346101919F9040B911316240486F900007160621123918009C8309BFD0641D36D",
            "+CMGL: 2,0,\"\",25",
            "07911346101919F9040B911316240486F900007160621123848006C733DAED1603",
            "+CMGL: 3,0,\"\",24",
            "07911346101919F9040B911316240486F900007160621123058005C7F3598C06",
            "OK"
        });

    final ListMessagesResponse listMessagesResponse = new ListMessagesResponse(response);
    final List<IndexMessage> messageList = listMessagesResponse.getIndexMessages();

    assertEquals(3, messageList.size());

    final IndexPduMessage message0 = (IndexPduMessage) messageList.get(0);
    assertEquals(1, message0.getIndex());
    assertEquals(MessageStatus.RECEIVED_UNREAD, message0.getStatus());
    assertEquals("", message0.getAlpha());
    assertEquals("07911346101919F9040B911316240486F900007160621123918009C8309BFD0641D36D", message0.getPdu());

    final IndexPduMessage message1 = (IndexPduMessage) messageList.get(1);
    assertEquals(2, message1.getIndex());
    assertEquals(MessageStatus.RECEIVED_UNREAD, message1.getStatus());
    assertEquals("", message1.getAlpha());
    assertEquals("07911346101919F9040B911316240486F900007160621123848006C733DAED1603", message1.getPdu());

    final IndexPduMessage message2 = (IndexPduMessage) messageList.get(2);
    assertEquals(3, message2.getIndex());
    assertEquals(MessageStatus.RECEIVED_UNREAD, message2.getStatus());
    assertEquals("", message2.getAlpha());
    assertEquals("07911346101919F9040B911316240486F900007160621123058005C7F3598C06", message2.getPdu());
  }
}