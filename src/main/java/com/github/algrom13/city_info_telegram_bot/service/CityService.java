package com.github.algrom13.city_info_telegram_bot.service;

import com.github.algrom13.city_info_telegram_bot.model.City;

import java.util.List;

public interface CityService {

    boolean addCity(City city);

    boolean updateCity(Long cityId, City city);

    boolean deleteCity(Long cityId);

    City getCityById(Long cityId);

    City getCityByName(String cityName);

    List<City> getCities();
}
