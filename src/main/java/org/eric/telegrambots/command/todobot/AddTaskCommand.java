package org.eric.telegrambots.command.todobot;

import com.pengrad.telegrambot.model.Update;
import org.eric.telegrambots.model.todobot.Task;
import org.eric.telegrambots.service.todobot.TaskService;
import org.eric.telegrambots.utils.SpringContext;

import java.util.Date;

public class AddTaskCommand extends Command {

    @Override
    public void run(Update update) {
        // 分割每個欄位
        String text = update.message().text();
        long chatId = update.message().chat().id();
        String[] columns = text.split(" ");

        // 檢查content字數
        String taskContent = columns.length >= 2 ? columns[1] : "";
        boolean check = this.checkContent(taskContent, update);
        if (!check) {
            return;
        }

        Task task = new Task();
        task.setChatId(chatId);
        task.setStatus(Task.TODO_STATUS);
        task.setContent(taskContent);
        task.setCreateTime(new Date());
        task.setUpdateTime(new Date());

        TaskService taskService = SpringContext.getBean(TaskService.class);
        task = taskService.addTask(task);

        String resultMsg = String.format("add task successful, your task id is %s .", task.getHash());
        sendMsg(resultMsg, update);
    }

    private boolean checkContent(String content, Update update) {
        if (content.length() > 200) {
            sendMsg("add task fail, your task content is more than 200 characters.", update);
            return false;
        }
        if (content.length() == 0) {
            sendMsg("add task fail, your task content is 0 character.", update);
            return false;
        }
        return true;
    }
}
