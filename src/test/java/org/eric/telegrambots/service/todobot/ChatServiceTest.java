package org.eric.telegrambots.service.todobot;

import org.eric.SuperTest;
import org.eric.telegrambots.model.todobot.Chat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

public class ChatServiceTest extends SuperTest {
    @Autowired
    private ChatService chatService;

    @Test
    @Transactional
    @Rollback(true)
    public void test_add_chat_should_success() {
        Chat chat = new Chat();
        chat.setId(123);
        chat.setFirstName("eric");
        chat.setLastName("shih");
        chat.setUsername("eric_shih_is_good");

        Chat result = chatService.addChat(chat);

        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_add_exists_chat_should_success() {
        Chat chat = new Chat();
        chat.setId(123);
        chat.setFirstName("eric");
        chat.setLastName("shih");
        chat.setUsername("eric_shih_is_good");
        chatService.addChat(chat);

        Chat chat2 = new Chat();
        chat2.setId(123);
        chat2.setFirstName("eric");
        chat2.setLastName("shih");
        chat2.setUsername("eric_shih_is_good");
        Chat result = chatService.addChat(chat2);

        Assert.assertNotNull(chat2);
        Assert.assertEquals(123, result.getId());
        Assert.assertEquals("eric", result.getFirstName());
    }
}
