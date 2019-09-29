package org.eric.telegrambots.command.todobot.schedule;

import com.pengrad.telegrambot.TelegramBot;
import org.eric.telegrambots.service.todobot.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

    @Autowired
    private AlertService alertService;

    @Scheduled(fixedDelay = 60 * 1000)
    public void sendAlert() {
        alertService.sendAlert();
    }
}
