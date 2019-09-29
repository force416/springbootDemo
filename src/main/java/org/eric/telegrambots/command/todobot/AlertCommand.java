package org.eric.telegrambots.command.todobot;

import com.pengrad.telegrambot.model.Update;
import org.eric.telegrambots.model.todobot.Alert;
import org.eric.telegrambots.service.todobot.AlertService;
import org.eric.telegrambots.utils.SpringContext;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

public class AlertCommand extends Command {

    @Override
    public void run(Update update) {
        // 分割每個欄位
        String text = update.message().text();
        long chatId = update.message().chat().id();
        String[] columns = text.split(" ");

        columns = this.removeArrayIndex(columns, 0);
        String time = columns.length > 1 ? columns[0] : "";

        // valid time
        if (!this.checkTime(time, update)) {
            return;
        }
        // get date
        Date alertDate = this.getAlertDate(time);

        // get content
        columns = this.removeArrayIndex(columns, 0);
        System.out.println(Arrays.toString(columns));
        String content = columns.length >= 1 ? Arrays.stream(columns).collect(Collectors.joining(" ")) : "";
        System.out.println(content);

        Alert alert = new Alert();
        alert.setChatId(chatId);
        alert.setContent(content);
        alert.setCreateTime(new Date());
        alert.setAlertTime(alertDate);

        AlertService alertService = SpringContext.getBean(AlertService.class);
        alertService.addAlert(alert);
    }

    private Date getAlertDate(String time) {

        String[] timeSplit = time.split("(?=(m|h|M|y))");
        int num = Integer.parseInt(timeSplit[0]);
        String suffix = timeSplit[1];

        Calendar calendar = Calendar.getInstance();
        switch (suffix) {
            case "m":
                calendar.add(Calendar.MINUTE, num);
                break;
            case "h":
                calendar.add(Calendar.HOUR, num);
                break;
            case "M":
                calendar.add(Calendar.MONTH, num);
                break;
            case "y":
                calendar.add(Calendar.YEAR, num);
                break;
            default:
                calendar = null;
                break;
        }

        return calendar == null ? null : calendar.getTime();
    }

    private String[] removeArrayIndex(String[] arrays, int index) {
        String[] newArrays = new String[arrays.length - 1];
        for (int i = 0, k = 0; i < arrays.length; i++) {
            if (i != index) {
                newArrays[k++] = arrays[i];
            }
        }
        return newArrays;
    }

    private boolean checkTime(String time, Update update) {
        boolean match = time.matches("^\\d{1,2}(m|h|M|y)$");
        if (!match) {
            sendMsg("please input correct time format", update);
        }
        return match;
    }

}
