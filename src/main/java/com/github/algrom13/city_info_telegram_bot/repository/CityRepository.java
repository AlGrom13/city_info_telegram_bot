package com.github.algrom13.city_info_telegram_bot.repository;

import com.github.algrom13.city_info_telegram_bot.repository.entity.CityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends CrudRepository<CityEntity, Long> {

    Optional<CityEntity> findByName(String name);
}
