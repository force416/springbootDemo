package org.eric.telegrambots.Repository.todobot;

import org.eric.telegrambots.model.todobot.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {

}
