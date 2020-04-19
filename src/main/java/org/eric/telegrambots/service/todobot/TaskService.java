package org.eric.telegrambots.service.todobot;

import org.eric.telegrambots.repository.todobot.TaskRepository;
import org.eric.telegrambots.model.todobot.Task;
import org.eric.telegrambots.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("taskService")
public class TaskService {

    private TaskRepository taskRepository;

    @Transactional(rollbackFor = Exception.class)
    public Task addTask(Task task) {
        task.setHash(UUID.getRandomId());

        return taskRepository.save(task);
    }

    public List<Task> getTodoTasks(long chatId) {
        Page<Task> queryResult = taskRepository.findTodoTasksByChatId(chatId, PageRequest.of(0,500));
        return queryResult.get().collect(Collectors.toList());
    }

    public List<Task> getDoneTasks(long chatId) {
        Page<Task> queryResult = taskRepository.findDoneTasksByChatId(chatId, PageRequest.of(0,500));
        return queryResult.get().collect(Collectors.toList());
    }

    public Task getTaskByHash(String hash) {
        return taskRepository.findTaskByHash(hash);
    }

    @Transactional(rollbackFor = Exception.class)
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
}
