package org.eric.telegrambots.command.todobot.schedule;

import org.eric.telegrambots.service.todobot.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleTask {

    private AlertService alertService;

    @Scheduled(fixedDelay = 60 * 1000)
    public void sendAlert() {
        alertService.sendAlert();
    }

    @Autowired
    public void setAlertService(AlertService alertService) {
        this.alertService = alertService;
    }
}
