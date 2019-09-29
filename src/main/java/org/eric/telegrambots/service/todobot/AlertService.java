package org.eric.telegrambots.service.todobot;

import org.eric.telegrambots.Repository.todobot.AlertRepository;
import org.eric.telegrambots.model.todobot.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("alertService")
public class AlertService {
    @Autowired
    private AlertRepository alertRepository;

    public Alert addAlert(Alert alert) {
        return alertRepository.save(alert);
    }
}
