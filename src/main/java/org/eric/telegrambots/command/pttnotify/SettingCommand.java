package org.eric.telegrambots.command.pttnotify;

import com.pengrad.telegrambot.model.Update;
import org.eric.telegrambots.model.pttnotify.ChatBoard;
import org.eric.telegrambots.service.pttnotify.NotifyService;
import org.eric.telegrambots.utils.SpringContext;

import java.util.List;

public class SettingCommand extends PTTNotifyBotCommand {

    @Override
    public void run(Update update) {
        long chatId = update.message().chat().id();
        NotifyService notifyService = SpringContext.getBean(NotifyService.class);
        List<ChatBoard> chatBoards = notifyService.getSubscribedBoard(chatId);

        if (chatBoards.size() > 0) {
            StringBuilder builder = new StringBuilder();
            builder.append("Board - Likelimit \n");
            chatBoards.stream().forEach((chatBoard) -> {
                builder.append(String.format("%s - %d \n", chatBoard.getBoard().getName(), chatBoard.getLikeLimit()));
            });

            sendMsg(builder.toString(), update);
        } else {
            sendMsg("You don't have any subscribed board yet.", update);
        }
    }

}
