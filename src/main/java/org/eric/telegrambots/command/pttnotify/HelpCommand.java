package org.eric.telegrambots.command.pttnotify;

import com.pengrad.telegrambot.model.Update;

public class HelpCommand extends PTTNotifyBotCommand {
    @Override
    public void run(Update update) {
        long chatId = update.message().chat().id();
        sendMsg(getHelpMsg(chatId));
    }
}
