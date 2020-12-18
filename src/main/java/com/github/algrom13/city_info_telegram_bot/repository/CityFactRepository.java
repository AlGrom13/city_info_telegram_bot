package com.github.algrom13.city_info_telegram_bot.repository;

import com.github.algrom13.city_info_telegram_bot.model.CityFact;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityFactEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityFactRepository extends CrudRepository<CityFactEntity, Long> {
}
