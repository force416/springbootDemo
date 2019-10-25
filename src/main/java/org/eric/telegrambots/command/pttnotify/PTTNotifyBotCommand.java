package org.eric.telegrambots.command.pttnotify;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardRemove;
import com.pengrad.telegrambot.request.SendMessage;
import org.eric.telegrambots.utils.SpringContext;

import java.util.HashMap;
import java.util.Map;

public abstract class PTTNotifyBotCommand {
    protected final static String START_COMMAND = "/start";
    protected final static String SUBSCRIBE_COMMAND = "/subscribe";
    protected final static String UNSUBSCRIBE_COMMAND = "/unsubscribe";
    protected final static String SETTING_COMMAND = "/setting";
    protected final static String STOP_COMMAND = "/stop";
    protected final static String HELP_COMMAND = "/help";

    private static Map<String, PTTNotifyBotCommand> commandMap = new HashMap<>();

    static {
        commandMap.put(START_COMMAND, new StartCommand());
        commandMap.put(SUBSCRIBE_COMMAND, new SubscribeCommand());
        commandMap.put(UNSUBSCRIBE_COMMAND, new UnsubscribeCommand());
        commandMap.put(SETTING_COMMAND, new SettingCommand());
        commandMap.put(STOP_COMMAND, new StopCommand());
        commandMap.put(HELP_COMMAND, new HelpCommand());
    }

    public abstract void run(Update update);

    public static void dispatch(Update update) {
        String text = update.message().text();
        text = text == null ? "" : text;

        String[] texts = text.split(" ");
        String action = texts[0];

        PTTNotifyBotCommand cmd = commandMap.getOrDefault(action, new HelpCommand());

        cmd.run(update);
    }

    protected static SendMessage getHelpMsg(long chatId) {
        StringBuilder builder = new StringBuilder();
        builder.append("Use this format to subscribe ptt board:\n");
        builder.append("/subscribe Gossiping 30 \n");
        builder.append("\n");
        builder.append("Use this format to unsubscribe ptt board:\n");
        builder.append("/unsubscribe Gossiping \n");
        builder.append("\n");
        builder.append("Use this format to get subscribed ptt boards:\n");
        builder.append("/setting \n");

        return new SendMessage(chatId, builder.toString());
    }

    protected static void sendMsg(String message, Update update) {
        long chatId = update.message().chat().id();
        getTelegramBot().execute(
                new SendMessage(chatId, message)
                        .replyMarkup(new ReplyKeyboardRemove())
        );
    }

    protected static void sendMsg(SendMessage sendMessage) {
        getTelegramBot().execute(
                sendMessage.replyMarkup(new ReplyKeyboardRemove())
        );
    }

    protected static TelegramBot getTelegramBot() {
        return (TelegramBot) SpringContext.getBean("pttNotifyBot");
    }
}
