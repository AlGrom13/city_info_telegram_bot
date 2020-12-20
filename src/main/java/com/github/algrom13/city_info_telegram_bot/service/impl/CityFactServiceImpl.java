package com.github.algrom13.city_info_telegram_bot.service.impl;

import com.github.algrom13.city_info_telegram_bot.model.City;
import com.github.algrom13.city_info_telegram_bot.model.CityFact;
import com.github.algrom13.city_info_telegram_bot.repository.CityFactRepository;
import com.github.algrom13.city_info_telegram_bot.repository.CityRepository;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityEntity;
import com.github.algrom13.city_info_telegram_bot.repository.entity.CityFactEntity;
import com.github.algrom13.city_info_telegram_bot.service.CityFactService;
import com.github.algrom13.city_info_telegram_bot.service.convertor.CityConvertor;
import com.github.algrom13.city_info_telegram_bot.service.convertor.CityFactConvertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CityFactServiceImpl implements CityFactService {

    private CityRepository cityRepository;
    private CityFactRepository cityFactRepository;

    @Autowired
    public CityFactServiceImpl(CityRepository cityRepository, CityFactRepository cityFactRepository) {
        this.cityRepository = cityRepository;
        this.cityFactRepository = cityFactRepository;
    }

    @Override
    public boolean addFact(Long cityId, CityFact cityFact) {
        try {
            CityEntity cityInDB = cityRepository.findById(cityId).orElse(null);
            if (cityInDB == null) {
                return false;
            }
            CityFactEntity cityFactEntity = CityFactConvertor.toEntity(cityFact);
            cityFactEntity.setCityEntity(cityInDB);
            cityInDB.getCityFactEntityList().add(cityFactEntity);
            cityRepository.save(cityInDB);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public CityFact getFactById(Long id) {
        Optional<CityFactEntity> cityFactEntityOptional = cityFactRepository.findById(id);
        if (cityFactEntityOptional.isPresent()) {
            CityFactEntity cityFactEntity = cityFactEntityOptional.get();
            Long cityId = cityFactEntity.getCityEntity().getId();
            CityFact cityFact = CityFactConvertor.fromEntity(cityFactEntity);
            cityFact.setCityId(cityId);
            return cityFact;
        }
        return null;
    }

    @Override
    public List<CityFact> getFactsAboutCity(Long cityId) {
        Optional<CityEntity> cityOptional = cityRepository.findById(cityId);
        if (cityOptional.isPresent()) {
            CityEntity cityEntity = cityOptional.get();
            City city = CityConvertor.fromEntity(cityEntity);
            return city.getCityFacts();
        }
        return null;
    }

    @Override
    public boolean updateFact(Long factId, CityFact cityFact) {
        try {
            CityFactEntity factEntityInDB = cityFactRepository.findById(factId).orElse(null);
            if (factEntityInDB != null) {
                factEntityInDB.setFact(cityFact.getFact());
                cityFactRepository.save(factEntityInDB);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteFact(Long cityId, Long factId) {
        try {
            CityEntity cityFromDB = cityRepository.findById(cityId).orElse(null);
            if (cityFromDB != null) {
                CityFactEntity cityFactEntityForRemove =
                        findFactEntityById(factId, cityFromDB.getCityFactEntityList());
                if (cityFactEntityForRemove == null) {
                    return false;
                }
                cityFromDB.getCityFactEntityList().remove(cityFactEntityForRemove);
                cityRepository.save(cityFromDB);
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private CityFactEntity findFactEntityById(Long id, List<CityFactEntity> cityFactEntityList) {
        return cityFactEntityList.stream()
                .filter(cityFactEntity -> cityFactEntity.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
