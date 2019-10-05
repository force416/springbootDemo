package org.eric.telegrambots.repository.pttnotify;

import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatBoardRepository extends JpaRepository<ChatBoard, Long> {
}
