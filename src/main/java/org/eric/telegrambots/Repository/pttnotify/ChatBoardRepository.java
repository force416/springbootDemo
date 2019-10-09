package org.eric.telegrambots.repository.pttnotify;

import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatBoardRepository extends JpaRepository<ChatBoard, Long> {

    Optional<ChatBoard> findByChatIdAndBoardId(long chatId, long boardId);

    @Query(value = "SELECT DISTINCT b.name " +
            "FROM chats_boards c " +
            "JOIN boards b ON c.board_id = b.id ", nativeQuery = true)
    List<String> findDistinctBoard();

    @Query(value = "DELETE FROM chats_boards c " +
            "where c.chat_id = :chatId AND c.board_id = :boardId ", nativeQuery = true)
    long deleteByChatIdAndBoardId(@Param("chatId") long chatId, @Param("boardId") long boardId);
}