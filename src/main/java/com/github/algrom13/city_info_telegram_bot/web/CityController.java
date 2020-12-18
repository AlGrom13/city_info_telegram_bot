package com.github.algrom13.city_info_telegram_bot.web;

import com.github.algrom13.city_info_telegram_bot.model.City;
import com.github.algrom13.city_info_telegram_bot.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @PostMapping(value = "/cities")
    public ResponseEntity<?> create(@RequestBody City city) {
        boolean isAdded = cityService.addCity(city);
        return isAdded ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/cities")
    public ResponseEntity<List<City>> read() {
        List<City> cities = cityService.getCities();
        return cities != null && !cities.isEmpty() ?
                new ResponseEntity<>(cities, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cities/{id}")
    public ResponseEntity<City> read(@PathVariable(name = "id") Long id) {
        City city = cityService.getCityById(id);

        return city != null ?
                new ResponseEntity<>(city, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/cities/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") Long id, @RequestBody City city) {
        boolean updated = cityService.updateCity(id, city);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/cities/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        boolean deleted = cityService.deleteCity(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
