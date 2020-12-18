package com.github.algrom13.city_info_telegram_bot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotUpdateProcessor {

    SendMessage process(Update update);
}
