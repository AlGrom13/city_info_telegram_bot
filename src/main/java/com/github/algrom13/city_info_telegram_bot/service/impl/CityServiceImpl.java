package com.github.algrom13.city_info_telegram_bot.service.impl;

import com.github.algrom13.city_info_telegram_bot.model.City;
import com.github.algrom13.city_info_telegram_bot.repository.CityRepository;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityEntity;
import com.github.algrom13.city_info_telegram_bot.service.CityService;
import com.github.algrom13.city_info_telegram_bot.service.convertor.CityConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public boolean addCity(City city) {
        try {
            cityRepository.save(CityConvertor.toEntity(city));
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean updateCity(Long cityId, City city) {
        try {
            CityEntity cityInDB = cityRepository.findById(cityId).get();
            cityInDB.setName(city.getName());
            boolean hasNewFactList = city.getCityFacts() != null;
            boolean hasFactList = cityInDB.getCityFactEntityList() != null;
            if (hasNewFactList && hasFactList) {
                cityInDB.getCityFactEntityList().clear();
                CityEntity cityEntity = CityConvertor.toEntity(city);
                cityInDB.getCityFactEntityList().addAll(cityEntity.getCityFactEntityList());
            }
            cityRepository.save(cityInDB);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteCity(Long cityId) {
        try {
            cityRepository.deleteById(cityId);
            boolean isDeleted = !cityRepository.existsById(cityId);
            return isDeleted;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public City getCityById(Long cityId) {
        Optional<CityEntity> cityOptional = cityRepository.findById(cityId);
        return CityConvertor.fromEntity(cityOptional.orElse(null));
    }

    @Override
    public City getCityByName(String cityName) {
        Optional<CityEntity> cityOptional = cityRepository.findByName(cityName);
        return CityConvertor.fromEntity(cityOptional.orElse(null));
    }

    @Override
    public List<City> getCities() {
        List<CityEntity> cityEntityList = (List<CityEntity>) cityRepository.findAll();
        return cityEntityList.stream()
                .map(CityConvertor::fromEntity)
                .collect(Collectors.toList());
    }
}
