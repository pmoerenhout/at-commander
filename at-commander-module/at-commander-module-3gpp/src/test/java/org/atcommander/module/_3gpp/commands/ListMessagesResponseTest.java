package org.atcommander.module._3gpp.commands;

import static org.junit.Assert.assertEquals;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.atcommander.AtResponse;
import org.atcommander.basic.commands.BaseCommandTest;
import org.atcommander.module._3gpp.types.ListMessage;
import org.atcommander.module.v250.enums.MessageStatus;
import org.junit.Assert;
import org.junit.Test;

public class ListMessagesResponseTest extends BaseCommandTest {

  @Test
  public void test_cmgl_text_rec_read() throws Exception {

    final AtResponse response = createAtResponse(
        new String[]{
            "+CMGL: 1,\"REC READ\",\"+31612345678\",\"\",\"13/12/17,02:34:52+04\"",
            "Test message",
            "OK"
        });

    final ListMessagesResponse listMessagesResponse = new ListMessagesResponse(response);

    final ListMessage listMessage = listMessagesResponse.getMessageList().get(0);
    assertEquals(1, listMessage.getIndex());
    assertEquals(MessageStatus.RECEIVED_READ, listMessage.getStatus());
    assertEquals("+31612345678", listMessage.getOada());
    assertEquals("", listMessage.getAlpha());
    assertEquals(ZonedDateTime.of(2017, 12, 13, 2, 34, 52, 0, ZoneId.of("+04")), listMessage.getScts());
  }

  @Test
  public void test_cmgl_pdu_rec_read() throws Exception {

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
    final List<ListMessage> messageList = listMessagesResponse.getMessageList();

    assertEquals(3, messageList.size());

    final ListMessage listMessage0 = messageList.get(0);
    assertEquals(1, listMessage0.getIndex());
    assertEquals(MessageStatus.RECEIVED_UNREAD, listMessage0.getStatus());
    Assert.assertNull(listMessage0.getOada());
    assertEquals("", listMessage0.getAlpha());
    //assertEquals(ZonedDateTime.of(2017,12,13,2,34,52,0, ZoneId.of("+04")), listMessage.getScts());
    assertEquals("07911346101919F9040B911316240486F900007160621123918009C8309BFD0641D36D", listMessage0.getPdu());

    final ListMessage listMessage1 = messageList.get(1);
    assertEquals(2, listMessage1.getIndex());
    assertEquals(MessageStatus.RECEIVED_UNREAD, listMessage1.getStatus());
    Assert.assertNull(listMessage1.getOada());
    assertEquals("", listMessage1.getAlpha());
    //assertEquals(ZonedDateTime.of(2017,12,13,2,34,52,0, ZoneId.of("+04")), listMessage.getScts());
    assertEquals("07911346101919F9040B911316240486F900007160621123848006C733DAED1603", listMessage1.getPdu());

    final ListMessage listMessage2 = messageList.get(2);
    assertEquals(3, listMessage2.getIndex());
    assertEquals(MessageStatus.RECEIVED_UNREAD, listMessage2.getStatus());
    Assert.assertNull(listMessage2.getOada());
    assertEquals("", listMessage2.getAlpha());
    //assertEquals(ZonedDateTime.of(2017,12,13,2,34,52,0, ZoneId.of("+04")), listMessage.getScts());
    assertEquals("07911346101919F9040B911316240486F900007160621123058005C7F3598C06", listMessage2.getPdu());
  }
}