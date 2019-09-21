package org.eric.command;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.eric.utils.SpringContext;

public class AddTaskCommand extends Command {

    @Override
    public void run(Update update) {
        long chatId = update.message().chat().id();
        TelegramBot telegramBot = SpringContext.getBean(TelegramBot.class);
        telegramBot.execute(new SendMessage(chatId, "add task successful."));
    }
}
