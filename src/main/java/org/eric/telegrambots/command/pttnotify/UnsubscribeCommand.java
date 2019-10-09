package org.eric.telegrambots.command.pttnotify;

import com.pengrad.telegrambot.model.Update;
import org.eric.telegrambots.service.pttnotify.NotifyService;
import org.eric.telegrambots.utils.SpringContext;

public class UnsubscribeCommand extends PTTNotifyBotCommand {

    @Override
    public void run(Update update) {
        // 分割每個欄位
        String text = update.message().text();
        long chatId = update.message().chat().id();
        String[] columns = text.split(" ");

        // board name
        String boardName = columns.length >= 2 ? columns[1] : null;


        if (boardName == null) {
            sendMsg("Please input correct board name.", update);
            return;
        } else {
            boardName = boardName.toLowerCase();
        }

        NotifyService notifyService = SpringContext.getBean(NotifyService.class);
        notifyService.unsubscribeBoard(chatId, boardName);

        sendMsg(String.format("subscribe board: %s success", boardName), update);
    }
}
