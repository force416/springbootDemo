package org.eric.telegrambots.repository.pttnotify;

import org.eric.SuperTest;
import org.eric.telegrambots.model.pttnotify.Board;
import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.eric.telegrambots.model.todobot.Chat;
import org.eric.telegrambots.repository.todobot.ChatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

public class ChatBoardRepositoryTest extends SuperTest {
    @Autowired
    private ChatBoardRepository chatBoardRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void test_query_chatboard_should_success() {
        Chat chat = new Chat();
        chat.setId(123);
        chat.setFirstName("eric");
        chat.setLastName("shih");
        chat.setUsername("eric_shih_is_good");
        chat = chatRepository.save(chat);

        Board board = new Board();
        board.setName("Gossiping");
        board = boardRepository.save(board);

        ChatBoard chatBoard = new ChatBoard();
        chatBoard.setBoard(board);
        chatBoard.setChat(chat);
        chatBoard.setLastNotifyPostId(12345678);
        chatBoard.setLikeLimit(50);
        chatBoard = chatBoardRepository.save(chatBoard);

        Optional<ChatBoard> chatBoardOpt = chatBoardRepository.findById(chatBoard.getId());
        ChatBoard result = chatBoardOpt.orElse(null);

        Assert.assertTrue(chatBoardOpt.isPresent());
        Assert.assertNotNull(result);

    }
}
