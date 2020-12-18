package com.github.algrom13.city_info_telegram_bot.service.convertor;

import com.github.algrom13.city_info_telegram_bot.model.City;
import com.github.algrom13.city_info_telegram_bot.model.CityFact;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityEntity;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityFactEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CityConvertor {

    public static City fromEntity(CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        } else {
            City city = new City(cityEntity.getId(), cityEntity.getName(), null);
            if (cityEntity.getCityFactEntityList() != null) {
                List<CityFact> cityFacts = cityEntity.getCityFactEntityList().stream()
                        .map(CityFactConvertor::fromEntity)
                        .filter(Objects::nonNull)
                        .peek(cityFact -> cityFact.setCityId(city.getId()))
                        .collect(Collectors.toList());
                city.setCityFacts(cityFacts);
            }
            return city;
        }
    }

    public static CityEntity toEntity(City city) {
        if (city == null) {
            return null;
        } else {
            CityEntity cityEntity = new CityEntity();
            cityEntity.setId(city.getId());
            cityEntity.setName(city.getName());
            if (city.getCityFacts() != null) {
                List<CityFactEntity> cityFactEntityList = city.getCityFacts().stream()
                        .map(CityFactConvertor::toEntity)
                        .filter(Objects::nonNull)
                        .peek(cityFactEntity -> cityFactEntity.setCityEntity(cityEntity))
                        .collect(Collectors.toList());
                cityEntity.setCityFactEntityList(cityFactEntityList);
            }
            return cityEntity;
        }
    }
}
