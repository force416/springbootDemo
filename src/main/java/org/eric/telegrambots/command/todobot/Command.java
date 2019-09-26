package org.eric.telegrambots.command.todobot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.SendMessage;
import org.eric.telegrambots.utils.SpringContext;

public abstract class Command {
    protected final static String ADD_TASK_COMMAND = "/add_task";
    protected final static String DELETE_TASK_COMMAND = "/delete_task";
    protected final static String LIST_TODO_TASK_COMMAND = "/list_todo_tasks";
    protected final static String LIST_DONE_TASK_COMMAND = "/list_done_tasks";
    protected final static String SET_TASK_DONE_COMMAND = "/set_task_done";

    public abstract void run(Update update);

    protected static void sendMsg(String message, Update update) {
        long chatId = update.message().chat().id();
        TelegramBot telegramBot = SpringContext.getBean(TelegramBot.class);

        telegramBot.execute(new SendMessage(chatId, message).replyMarkup(new ReplyKeyboardRemove()));
    }

    protected static void sendMsg(SendMessage sendMessage) {
        TelegramBot telegramBot = SpringContext.getBean(TelegramBot.class);
        telegramBot.execute(sendMessage.replyMarkup(new ReplyKeyboardRemove()));
    }

    public static void dispatch(Update update) {
        String text = update.message().text();
        text = text == null ? "" : text;

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
                telegramBot.execute(getHelpMsg(chatId));
                break;
        }

        if (cmd != null) {
            cmd.run(update);
        }
    }

    protected static SendMessage getHelpMsg(long chatId) {
        StringBuilder builder = new StringBuilder();
        builder.append("Use this format to create task:\n");
        builder.append("/add_task text\n");
        builder.append("\n");
        builder.append("Use this format to delete task:\n");
        builder.append("/delete_task hashId\n");
        builder.append("\n");
        builder.append("Use /list_todo_tasks to list todo tasks:\n");
        builder.append("\n");
        builder.append("Use /list_done_tasks to list completed tasks:\n");
        builder.append("\n");
        builder.append("Use this format to set task completed:\n");
        builder.append("/set_task_done hashId\n");

        return new SendMessage(chatId, builder.toString());
    }
}
