package org.eric.telegrambots.command.pttnotify;

import com.pengrad.telegrambot.model.Update;
import org.eric.telegrambots.service.pttnotify.NotifyService;
import org.eric.telegrambots.utils.SpringContext;

public class StopCommand extends PTTNotifyBotCommand {

    @Override
    public void run(Update update) {
        long chatId = update.message().chat().id();
        NotifyService notifyService = SpringContext.getBean(NotifyService.class);
        notifyService.unSubscribeByChatId(chatId);
    }
}
