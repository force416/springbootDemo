package org.eric.controller;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HookController {

    @Autowired
    private TelegramBot telegramBot;

    @RequestMapping(path = {"/bot/hook/eric_bot"}, method = RequestMethod.POST)
    public String hook(@RequestBody String payload) {
        Update update = BotUtils.parseUpdate(payload);

        // Send messages
        long chatId = update.message().chat().id();
        telegramBot.execute(new SendMessage(chatId, update.message().text()));

        return "OK";
    }
}
