package org.eric.telegrambots.service.pttnotify;

import org.eric.telegrambots.exception.NotFoundException;
import org.eric.telegrambots.model.pttnotify.Board;
import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.eric.telegrambots.model.todobot.Chat;
import org.eric.telegrambots.repository.pttnotify.BoardRepository;
import org.eric.telegrambots.repository.pttnotify.ChatBoardRepository;
import org.eric.telegrambots.repository.todobot.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("notifyService")
public class NotifyService {

    @Autowired
    private ChatBoardRepository chatBoardRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private BoardRepository boardRepository;

    public ChatBoard subscribeBoard(long chatId, String boardName, int likeLimit) {
        Optional<Board> boardOptional = boardRepository.findByName(boardName);
        if (!boardOptional.isPresent()) {
            throw new NotFoundException(String.format("Can't find board: %s", boardName));
        }

        Optional<Chat> chatOptional = chatRepository.findById(chatId);

        ChatBoard chatBoard = new ChatBoard();
        chatBoard.setChat(chatOptional.get());
        chatBoard.setBoard(boardOptional.get());
        chatBoard.setLikeLimit(likeLimit);
        chatBoard.setLastNotifyPostId(0L);
        chatBoard = chatBoardRepository.save(chatBoard);

        return chatBoard;
    }
}