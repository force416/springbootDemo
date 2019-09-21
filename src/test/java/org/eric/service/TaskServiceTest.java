package org.eric.service;

import org.eric.SuperTest;
import org.eric.model.Task;
import org.eric.model.Chat;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

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
        System.out.println(task.getHash());
    }
}
