package org.eric.telegrambots.service.pttnotify;

import org.eric.telegrambots.exception.NotFoundException;
import org.eric.telegrambots.model.pttnotify.Board;
import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.eric.telegrambots.model.pttnotify.Post;
import org.eric.telegrambots.model.todobot.Chat;
import org.eric.telegrambots.repository.pttnotify.BoardRepository;
import org.eric.telegrambots.repository.pttnotify.ChatBoardRepository;
import org.eric.telegrambots.repository.todobot.ChatRepository;
import org.eric.telegrambots.utils.pttnotify.PTTParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("notifyService")
public class NotifyService {

    private ChatBoardRepository chatBoardRepository;

    private ChatRepository chatRepository;

    private BoardRepository boardRepository;

    public ChatBoard subscribeBoard(long chatId, String boardName, int likeLimit) {
        Optional<Board> boardOptional = boardRepository.findByName(boardName);
        if (!boardOptional.isPresent()) {
            throw new NotFoundException(String.format("Can't find board: %s", boardName));
        }

        Optional<Chat> chatOptional = chatRepository.findById(chatId);

        Optional<ChatBoard> chatBoardOptional = chatBoardRepository
                .findByChatIdAndBoardId(chatOptional.get().getId(), boardOptional.get().getId());

        // add or update
        ChatBoard chatBoard;
        if (chatBoardOptional.isPresent()) {
            chatBoard = chatBoardOptional.get();
            chatBoard.setLikeLimit(likeLimit);
        } else {
            chatBoard = new ChatBoard();
            chatBoard.setChat(chatOptional.get());
            chatBoard.setBoard(boardOptional.get());
            chatBoard.setLikeLimit(likeLimit);
            chatBoard.setLastNotifyPostId(0L);
        }
        chatBoard = chatBoardRepository.save(chatBoard);

        return chatBoard;
    }

    public void unsubscribeBoard(long chatId, String boardName) {
        Optional<Board> boardOptional = boardRepository.findByName(boardName);
        if (!boardOptional.isPresent()) {
            throw new NotFoundException(String.format("Can't find board: %s", boardName));
        }

        chatBoardRepository.deleteByChatIdAndBoardId(chatId, boardOptional.get().getId());
    }

    public ChatBoard updateChatBoardLastNotifyPostId(long chatId, String boardName, long lastNotifyPostId) {
        Optional<Board> boardOptional = boardRepository.findByName(boardName);
        if (!boardOptional.isPresent()) {
            throw new NotFoundException(String.format("Can't find board: %s", boardName));
        }

        Optional<Chat> chatOptional = chatRepository.findById(chatId);

        Optional<ChatBoard> chatBoardOptional = chatBoardRepository
                .findByChatIdAndBoardId(chatOptional.get().getId(), boardOptional.get().getId());

        ChatBoard chatBoard = chatBoardOptional.get();
        chatBoard.setLastNotifyPostId(lastNotifyPostId);

        return chatBoardRepository.save(chatBoard);
    }

    public Map<Long, Map<String, List<Post>>> getChatsNotifyPosts() {
        List<String> distinctBoards = chatBoardRepository.findDistinctBoard();
        List<ChatBoard> chatBoards = chatBoardRepository.findAll();
        Map<Long, Map<String, List<Post>>> chatPostsMap = new HashMap<>();

        distinctBoards.stream().forEach((boardName) -> {
            PTTParser pttParser = new PTTParser(boardName);
            List<Post> posts = pttParser.getPosts(5, 0);

            chatBoards.stream().forEach((chatBoard) -> {
                boolean isSameBoardName = chatBoard.getBoard().getName().equals(boardName);
                if (isSameBoardName) {
                    List<Post> chatPosts = posts.stream()
                            .filter((post -> post.getLike() >= chatBoard.getLikeLimit()))
                            .filter((post) -> post.getId() > chatBoard.getLastNotifyPostId())
                            .collect(Collectors.toList());

                    Map<String, List<Post>> boardPostsMap = chatPostsMap.get(chatBoard.getChat().getId());
                    if (boardPostsMap == null) {
                        boardPostsMap = new HashMap<>();
                    }

                    boardPostsMap.put(boardName, chatPosts);
                    chatPostsMap.put(chatBoard.getChat().getId(), boardPostsMap);
                }
            });
        });

        return chatPostsMap;
    }

    public List<ChatBoard> getSubscribedBoard(long chatId) {
        return chatBoardRepository.findByChatId(chatId);
    }

    public void unSubscribeByChatId(long chatId) {
        chatBoardRepository.deleteByChatId(chatId);
    }

    @Autowired
    public void setChatBoardRepository(ChatBoardRepository chatBoardRepository) {
        this.chatBoardRepository = chatBoardRepository;
    }

    @Autowired
    public void setChatRepository(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
}
