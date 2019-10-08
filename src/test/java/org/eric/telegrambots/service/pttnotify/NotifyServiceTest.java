package org.eric.telegrambots.service.pttnotify;

import org.eric.SuperTest;
import org.eric.telegrambots.model.pttnotify.Board;
import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.eric.telegrambots.model.pttnotify.Post;
import org.eric.telegrambots.model.todobot.Chat;
import org.eric.telegrambots.repository.pttnotify.BoardRepository;
import org.eric.telegrambots.repository.pttnotify.ChatBoardRepository;
import org.eric.telegrambots.repository.todobot.ChatRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NotifyServiceTest extends SuperTest {

    @Autowired
    private NotifyService notifyService;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ChatBoardRepository chatBoardRepository;

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

    @Test
    @Transactional
    @Rollback(true)
    public void test_getChatsNotifyPosts_should_success() {
        Chat chat = new Chat();
        chat.setId(123);
        chat.setFirstName("eric");
        chat.setLastName("shih");
        chat.setUsername("eric_shih_is_good");
        chat = chatRepository.save(chat);

        Chat chat2 = new Chat();
        chat2.setId(456);
        chat2.setFirstName("kali");
        chat2.setLastName("kam");
        chat2.setUsername("kali_kam_is_good");
        chat2 = chatRepository.save(chat2);

        Optional<Board> board = boardRepository.findByName("sex");
        Optional<Board> board2 = boardRepository.findByName("gossiping");

        ChatBoard chatBoard = new ChatBoard();
        chatBoard.setBoard(board.get());
        chatBoard.setChat(chat);
        chatBoard.setLastNotifyPostId(12345678);
        chatBoard.setLikeLimit(10);
        chatBoard = chatBoardRepository.save(chatBoard);

        ChatBoard chatBoard2 = new ChatBoard();
        chatBoard2.setBoard(board2.get());
        chatBoard2.setChat(chat);
        chatBoard2.setLastNotifyPostId(12345678);
        chatBoard2.setLikeLimit(10);
        chatBoard2 = chatBoardRepository.save(chatBoard2);

        ChatBoard chatBoard3 = new ChatBoard();
        chatBoard3.setBoard(board.get());
        chatBoard3.setChat(chat2);
        chatBoard3.setLastNotifyPostId(12345678);
        chatBoard3.setLikeLimit(30);
        chatBoard3 = chatBoardRepository.save(chatBoard3);

        ChatBoard chatBoard4 = new ChatBoard();
        chatBoard4.setBoard(board2.get());
        chatBoard4.setChat(chat2);
        chatBoard4.setLastNotifyPostId(12345678);
        chatBoard4.setLikeLimit(30);
        chatBoard4 = chatBoardRepository.save(chatBoard4);

        Map<Long, Map<String, List<Post>>> result = notifyService.getChatsNotifyPosts();

        Assert.assertNotNull(result.get(chat.getId()));
        Assert.assertNotNull(result.get(chat2.getId()));
    }
}
