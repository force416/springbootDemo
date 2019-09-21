package org.eric.controller;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import org.eric.command.Command;
import org.eric.model.Chat;
import org.eric.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HookController {

    @Autowired
    private ChatService chatService;

    @RequestMapping(path = {"/bot/hook/eric_bot"}, method = RequestMethod.POST)
    public String hook(@RequestBody String payload) {
        Update update = BotUtils.parseUpdate(payload);

        // check
        if (!this.checkMessage(update)) {
            return "NOT OK";
        };

        // save chatInfo
        Chat chat = this.copyTelegramChat(update);
        chatService.addChat(chat);

        // command dispatch
        Command.dispatch(update);

        return "OK";
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

    private boolean checkMessage(Update update) {
        Message message = update.message();
        if (message == null) {
            return false;
        }
        return true;
    }
}
