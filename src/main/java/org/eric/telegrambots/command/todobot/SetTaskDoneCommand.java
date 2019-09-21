package org.eric.telegrambots.command.todobot;

import com.pengrad.telegrambot.model.Update;
import org.eric.telegrambots.model.todobot.Task;
import org.eric.telegrambots.service.todobot.TaskService;
import org.eric.telegrambots.utils.SpringContext;

import java.util.Date;

public class SetTaskDoneCommand extends Command {
    @Override
    public void run(Update update) {
        long chatId = update.message().chat().id();

        // 分割每個欄位
        String text = update.message().text();
        String[] columns = text.split(" ");

        // 檢查task hash
        TaskService taskService = SpringContext.getBean(TaskService.class);
        String hashId = columns.length >= 2 ? columns[1] : "";

        Task task = taskService.getTaskByHash(hashId);
        if (task == null) {
            this.sendMsg(String.format("can't find task with [ %s ]", hashId), update);
            return;
        }

        if (task.getChatId() != chatId) {
            this.sendMsg(String.format("permission denied! you can't update this task", hashId), update);
            return;
        }

        task.setStatus(Task.DONE_STATUS);
        task.setUpdateTime(new Date());
        taskService.updateTask(task);

        this.sendMsg(String.format("set task [ %s ] done successful", hashId), update);
    }
}
