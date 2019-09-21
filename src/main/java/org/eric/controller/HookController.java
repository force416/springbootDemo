package org.eric.controller;

import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.model.Update;
import org.eric.command.Command;
import org.eric.model.User;
import org.eric.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HookController {

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/bot/hook/eric_bot"}, method = RequestMethod.POST)
    public String hook(@RequestBody String payload) {
        Update update = BotUtils.parseUpdate(payload);

        // save userInfo
        User user = this.copyTelegramUser(update);
        userService.addUser(user);

        Command.dispatch(update);

        return "OK";
    }

    private User copyTelegramUser(Update update) {
        com.pengrad.telegrambot.model.User telegramUser = update.message().from();
        User user = new User();
        user.setId(telegramUser.id());
        user.setFirstName(telegramUser.firstName());
        user.setLastName(telegramUser.lastName());
        user.setUsername(telegramUser.username());

        return user;
    }
}
