package org.eric.telegrambots.service.pttnotify;

import org.eric.SuperTest;
import org.eric.telegrambots.model.pttnotify.Board;
import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.eric.telegrambots.model.todobot.Chat;
import org.eric.telegrambots.repository.pttnotify.BoardRepository;
import org.eric.telegrambots.repository.todobot.ChatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

public class NotifyServiceTest extends SuperTest {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void test_subscribeBoard_should_success() {
        Chat chat = new Chat();
        chat.setId(123);
        chat.setFirstName("eric");
        chat.setLastName("shih");
        chat.setUsername("eric_shih_is_good");
        chat = chatRepository.save(chat);

        Board board = new Board();
        board.setName("Gossiping");
        board = boardRepository.save(board);

        ChatBoard chatBoard = notifyService.subscribeBoard(chat.getId(), board.getName(), 99);

        Assert.assertNotNull(chatBoard);
        Assert.assertEquals(123L, chatBoard.getChat().getId());
        Assert.assertEquals("Gossiping", chatBoard.getBoard().getName());
    }
}
