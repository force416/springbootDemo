package org.eric.Repository;

import org.eric.model.Task;
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
    List<Task> findTodoTasksByChatId(@Param("chatId") long chatId);

    @Query("SELECT t " +
            "FROM Task t " +
            "WHERE t.chatId = :chatId AND t.deleted = false AND t.status = 'done' " +
            "ORDER BY t.updateTime")
    List<Task> findDoneTasksByChatId(@Param("chatId") long chatId);

    @Query("SELECT t FROM Task t WHERE t.hash = :hash AND t.deleted = false")
    Task findTaskByHash(@Param("hash") String hash);

}
