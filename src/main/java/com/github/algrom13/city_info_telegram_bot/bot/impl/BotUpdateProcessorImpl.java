package com.github.algrom13.city_info_telegram_bot.bot.impl;

import com.github.algrom13.city_info_telegram_bot.bot.BotUpdateProcessor;
import com.github.algrom13.city_info_telegram_bot.model.City;
import com.github.algrom13.city_info_telegram_bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

@Component
public class BotUpdateProcessorImpl implements BotUpdateProcessor {

    private final String startMessageText = "Hello! I can talk you many facts about different cities!\n" +
            "Send me name of the city and I'll tell you about it.";

    private final String noCityMessageText = "Sorry! =(\n" +
            "I don't know about this city. Try another city.";

    private final String noFactsMessageText = "Ops! I know this city, but I don't know any facts about it...";

    private CityService cityService;

    @Autowired
    public BotUpdateProcessorImpl(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public SendMessage process(Update update) {
        String command = update.getMessage().getText();
        if ("/start".equals(command)) {
            return executeStartCommand(update);
        }
        return sendInfoAboutCity(update);
    }

    private SendMessage executeStartCommand(Update update) {
        SendMessage startMessage = new SendMessage();
        startMessage.setChatId(update.getMessage().getChatId().toString());
        startMessage.setText(startMessageText);
        return startMessage;
    }

    private SendMessage sendInfoAboutCity(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String cityName = update.getMessage().getText();
        City city = cityService.getCityByName(cityName);
        if (city == null) {
            SendMessage noSuchCityMessage = new SendMessage();
            noSuchCityMessage.setChatId(chatId);
            noSuchCityMessage.setText(noCityMessageText);
            return noSuchCityMessage;
        }
        if (city.getCityFacts() == null || city.getCityFacts().isEmpty()) {
            SendMessage noFactsAboutCityMessage = new SendMessage();
            noFactsAboutCityMessage.setChatId(chatId);
            noFactsAboutCityMessage.setText(noFactsMessageText);
            return noFactsAboutCityMessage;
        }
        SendMessage cityInfoMessage = new SendMessage();
        cityInfoMessage.setChatId(chatId);
        StringBuilder cityInfo = new StringBuilder();
        city.getCityFacts().stream()
                .filter(Objects::nonNull)
                .forEach(cityFact -> cityInfo.append(cityFact.getFact()).append("\n"));
        cityInfoMessage.setText(cityInfo.toString());
        return cityInfoMessage;
    }
}
