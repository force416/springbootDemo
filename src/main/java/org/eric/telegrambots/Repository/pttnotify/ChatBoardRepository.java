package org.eric.telegrambots.repository.pttnotify;

import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatBoardRepository extends JpaRepository<ChatBoard, Long> {

    Optional<ChatBoard> findByChatIdAndBoardId(long chatId, long boardId);

    @Query(value = "SELECT DISTINCT board_id FROM chats_boards", nativeQuery = true)
    List<Long> findDistinctBoardId();
}
