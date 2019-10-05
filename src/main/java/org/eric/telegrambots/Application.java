package org.eric.telegrambots;

import com.pengrad.telegrambot.TelegramBot;
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
    public TelegramBot getBot(@Value("${telegram.bot.token}") String token) {
        return new TelegramBot(token);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
