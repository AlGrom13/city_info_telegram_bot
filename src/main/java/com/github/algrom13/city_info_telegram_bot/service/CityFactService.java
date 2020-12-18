package com.github.algrom13.city_info_telegram_bot.service;

import com.github.algrom13.city_info_telegram_bot.model.CityFact;

import java.util.List;

public interface CityFactService {

    boolean addFact(Long cityId, CityFact cityFact);

    CityFact getFactById(Long id);

    List<CityFact> getFactsAboutCity(Long cityId);

    boolean updateFact(Long factId, CityFact cityFact);

    boolean deleteFact(Long cityId, Long cityFactId);
}
