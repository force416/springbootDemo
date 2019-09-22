package org.eric.telegrambots.Repository.todobot;

import org.eric.telegrambots.model.todobot.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT t " +
            "FROM Task t " +
            "WHERE t.chatId = :chatId AND t.deleted = false AND t.status = 'todo' " +
            "ORDER BY t.createTime")
    Page<Task> findTodoTasksByChatId(@Param("chatId") long chatId, Pageable pageable);

    @Query("SELECT t " +
            "FROM Task t " +
            "WHERE t.chatId = :chatId AND t.deleted = false AND t.status = 'done' " +
            "ORDER BY t.updateTime")
    Page<Task> findDoneTasksByChatId(@Param("chatId") long chatId, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.hash = :hash AND t.deleted = false")
    Task findTaskByHash(@Param("hash") String hash);

}
