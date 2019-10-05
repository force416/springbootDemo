package org.eric.telegrambots.service.todobot;

import org.eric.SuperTest;
import org.eric.telegrambots.model.todobot.Chat;
import org.eric.telegrambots.model.todobot.Task;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

public class TaskServiceTest extends SuperTest {
    @Autowired
    private TaskService taskService;

    @Autowired
    private ChatService chatService;

    @Test
    @Transactional
    @Rollback(true)
    public void test_add_task_should_success() {
        Chat chat = new Chat();
        chat.setId(111);
        chat = chatService.addChat(chat);

        Task task = new Task();
        task.setChatId(chat.getId());
        task.setContent("eat lunch");
        task.setDeleted(false);
        task.setStatus(Task.TODO_STATUS);
        task = taskService.addTask(task);

        Assert.assertNotNull(task);
        Assert.assertEquals(111, task.getChatId());
        Assert.assertTrue(task.getId() > 0);
    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_find_todo_tasks_should_success() {
        Chat chat = new Chat();
        chat.setId(111);
        chat = chatService.addChat(chat);

        Task task = new Task();
        task.setChatId(chat.getId());
        task.setContent("eat lunch");
        task.setDeleted(false);
        task.setStatus(Task.TODO_STATUS);
        taskService.addTask(task);

        Task task2 = new Task();
        task2.setChatId(chat.getId());
        task2.setContent("eat lunch");
        task2.setDeleted(false);
        task2.setStatus(Task.TODO_STATUS);
        taskService.addTask(task2);

        Task task3 = new Task();
        task3.setChatId(chat.getId());
        task3.setContent("eat lunch");
        task3.setDeleted(false);
        task3.setStatus(Task.TODO_STATUS);
        taskService.addTask(task3);

        List<Task> tasks = taskService.getTodoTasks(chat.getId());

        Assert.assertNotNull(tasks);
        Assert.assertTrue(tasks.size() > 0);
        Assert.assertEquals(3, tasks.size());

    }

    @Test
    @Transactional
    @Rollback(true)
    public void test_find_done_tasks_should_success() {
        Chat chat = new Chat();
        chat.setId(111);
        chat = chatService.addChat(chat);

        Task task = new Task();
        task.setChatId(chat.getId());
        task.setContent("eat lunch");
        task.setDeleted(false);
        task.setStatus(Task.DONE_STATUS);
        taskService.addTask(task);

        Task task2 = new Task();
        task2.setChatId(chat.getId());
        task2.setContent("eat lunch");
        task2.setDeleted(false);
        task2.setStatus(Task.DONE_STATUS);
        taskService.addTask(task2);

        Task task3 = new Task();
        task3.setChatId(chat.getId());
        task3.setContent("eat lunch");
        task3.setDeleted(false);
        task3.setStatus(Task.DONE_STATUS);
        taskService.addTask(task3);

        List<Task> tasks = taskService.getDoneTasks(chat.getId());

        Assert.assertNotNull(tasks);
        Assert.assertTrue(tasks.size() > 0);
        Assert.assertEquals(3, tasks.size());

    }
}
