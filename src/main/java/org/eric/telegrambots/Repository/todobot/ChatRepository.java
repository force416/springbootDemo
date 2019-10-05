package org.eric.telegrambots.repository.todobot;

import org.eric.telegrambots.model.todobot.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
