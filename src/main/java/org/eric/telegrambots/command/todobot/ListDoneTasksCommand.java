package org.eric.telegrambots.command.todobot;

import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.eric.telegrambots.model.todobot.Task;
import org.eric.telegrambots.service.todobot.TaskService;
import org.eric.telegrambots.utils.SpringContext;

import java.text.SimpleDateFormat;
import java.util.List;

public class ListDoneTasksCommand extends Command {
    @Override
    public void run(Update update) {
        long chatId = update.message().chat().id();

        TaskService taskService = SpringContext.getBean(TaskService.class);
        List<Task> tasks = taskService.getDoneTasks(chatId);

        String rep = tasks.stream()
                .map((task) -> {
                    String hash = task.getHash();
                    String content = task.getContent();
                    String updateTime =
                            new SimpleDateFormat("yyyy-MM-dd HH:mm")
                                    .format(task.getUpdateTime());
                    return String.format("[ %s ] %s at %s \r\n", hash, content, updateTime);
                })
                .reduce("", (str, content) -> {
                    return str + content;
                });

        if (rep == null || rep == "") {
            rep = "you are not complete any task yet.";
        }

        this.sendMsg(new SendMessage(chatId, rep)
                .disableWebPagePreview(true));
    }
}
