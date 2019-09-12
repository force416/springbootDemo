package org.eric;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class App 
{
    @Bean
    public TelegramBot getBot(@Value("${telegram.bot.token}") String token) {
        return new TelegramBot(token);
    }

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
