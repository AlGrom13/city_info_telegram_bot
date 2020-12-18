package com.github.algrom13.city_info_telegram_bot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    @Value("${bot.token}")
    private String botToken;

    private BotUpdateProcessor botUpdateProcessor;

    public BotUpdateProcessor getBotUpdateProcessor() {
        return botUpdateProcessor;
    }

    @Autowired
    public void setBotUpdateProcessor(BotUpdateProcessor botUpdateProcessor) {
        this.botUpdateProcessor = botUpdateProcessor;
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = botUpdateProcessor.process(update);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
