package org.eric.telegrambots;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application
{
    @Bean
    @Qualifier("todoBot")
    public TelegramBot getToDoBot(@Value("${telegram.bot.todoBot.token}") String token) {
        return new TelegramBot(token);
    }

    @Bean
    @Qualifier("pttNotifyBot")
    public TelegramBot getPTTNotifyBot(@Value("${telegram.bot.pttNotifyBot.token}") String token) {
        return new TelegramBot(token);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
