package org.eric.telegrambots.repository.pttnotify;

import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatBoardRepository extends JpaRepository<ChatBoard, Long> {

    Optional<ChatBoard> findByChatIdAndBoardId(long chatId, long boardId);
}
