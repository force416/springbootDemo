package org.eric.telegrambots.repository.pttnotify;

import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatBoardRepository extends JpaRepository<ChatBoard, Long> {

    Optional<ChatBoard> findByChatIdAndBoardId(long chatId, long boardId);

    List<ChatBoard> findByChatId(long chatId);

    @Query(value = "SELECT DISTINCT b.name " +
            "FROM chats_boards c " +
            "JOIN boards b ON c.board_id = b.id ", nativeQuery = true)
    List<String> findDistinctBoard();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chats_boards c " +
            "where c.chat_id = :chatId AND c.board_id = :boardId", nativeQuery = true)
    void deleteByChatIdAndBoardId(@Param("chatId") long chatId, @Param("boardId") long boardId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM chats_boards c " +
            "where c.chat_id = :chatId", nativeQuery = true)
    void deleteByChatId(@Param("chatId") long chatId);
}