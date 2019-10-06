package org.eric.telegrambots.command.pttnotify;

import com.pengrad.telegrambot.model.Update;
import org.eric.telegrambots.model.todobot.Chat;
import org.eric.telegrambots.service.todobot.ChatService;
import org.eric.telegrambots.utils.SpringContext;

import java.util.Date;

public class StartCommand extends PTTNotifyBotCommand {

    @Override
    public void run(Update update) {
        ChatService chatService = SpringContext.getBean(ChatService.class);
        chatService.addChat(this.copyTelegramChat(update));

        long chatId = update.message().chat().id();
        sendMsg(getHelpMsg(chatId));
    }

    private Chat copyTelegramChat(Update update) {
        com.pengrad.telegrambot.model.Chat telegramChat = update.message().chat();
        Chat chat = new Chat();
        chat.setId(telegramChat.id());
        chat.setFirstName(telegramChat.firstName());
        chat.setLastName(telegramChat.lastName());
        chat.setUsername(telegramChat.username());
        chat.setCreateTime(new Date());

        return chat;
    }
}
