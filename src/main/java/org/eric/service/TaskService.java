package org.eric.service;

import org.eric.Repository.TaskRepository;
import org.eric.model.Task;
import org.eric.utils.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("taskService")
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(rollbackFor = Exception.class)
    public Task addTask(Task task) {
        task.setHash(UUID.getRandomId());

        return taskRepository.save(task);
    }

    public List<Task> getTodoTasks(long chatId) {
        return taskRepository.findTodoTasksByChatId(chatId);
    }

    public List<Task> getDoneTasks(long chatId) {
        return taskRepository.findDoneTasksByChatId(chatId);
    }

    public Task getTaskByHash(String hash) {
        return taskRepository.findTaskByHash(hash);
    }

    @Transactional(rollbackFor = Exception.class)
    public Task updateTask(Task task) {
        return taskRepository.save(task);
    }
}
