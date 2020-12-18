package com.github.algrom13.city_info_telegram_bot.repository;

import com.github.algrom13.city_info_telegram_bot.model.City;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @Test
    @Transactional
    void findByName() {
        CityEntity city = new CityEntity();
        city.setName("Москва");
        cityRepository.save(city);

        Optional<CityEntity> findCity = cityRepository.findByName("Москва");


        assertEquals(true, findCity.isPresent());
        assertEquals("Москва", findCity.get().getName());
    }
}