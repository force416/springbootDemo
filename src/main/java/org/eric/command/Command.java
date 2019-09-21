package org.eric.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.eric.utils.SpringContext;

public abstract class Command {
    private final static String ADD_TASK_COMMAND = "/add_task";
    private final static String DELETE_TASK_COMMAND = "/delete_task";
    private final static String LIST_TODO_TASK_COMMAND = "/list_todo_tasks";
    private final static String LIST_DONE_TASK_COMMAND = "/list_done_task";
    private final static String SET_TASK_DONE_COMMAND = "/set_task_done";

    public abstract void run(Update update);

    public static void dispatch(Update update) {
        String text = update.message().text();
        String[] texts = text.split(" ");
        String action = texts[0];

        Command cmd = null;
        switch (action) {
            case ADD_TASK_COMMAND:
                cmd = new AddTaskCommand();
                break;
            case DELETE_TASK_COMMAND:
                cmd = new DeleteTaskCommand();
                break;
            case LIST_TODO_TASK_COMMAND:
                cmd = new ListTodoTasksCommand();
                break;
            case LIST_DONE_TASK_COMMAND:
                cmd = new ListDoneTasksCommand();
                break;
            case SET_TASK_DONE_COMMAND:
                cmd = new SetTaskDoneCommand();
                break;
            default:
                long chatId = update.message().chat().id();
                TelegramBot telegramBot = SpringContext.getBean(TelegramBot.class);
                telegramBot.execute(new SendMessage(chatId, "please input correct commands."));
                break;
        }

        if (cmd != null) {
            cmd.run(update);
        }
    }
}
