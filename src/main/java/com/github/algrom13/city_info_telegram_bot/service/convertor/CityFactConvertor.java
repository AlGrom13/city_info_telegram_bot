package com.github.algrom13.city_info_telegram_bot.service.convertor;

import com.github.algrom13.city_info_telegram_bot.model.CityFact;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityFactEntity;

public class CityFactConvertor {

    public static CityFact fromEntity(CityFactEntity cityFactEntity) {
        if (cityFactEntity == null) {
            return null;
        } else {
            return new CityFact(cityFactEntity.getId(), cityFactEntity.getFact(), null);
        }
    }

    public static CityFactEntity toEntity(CityFact cityFact) {
        if (cityFact == null) {
            return null;
        } else {
            CityFactEntity cityFactEntity = new CityFactEntity();
            cityFactEntity.setId(cityFact.getId());
            cityFactEntity.setFact(cityFact.getFact());
            return cityFactEntity;
        }
    }
}
