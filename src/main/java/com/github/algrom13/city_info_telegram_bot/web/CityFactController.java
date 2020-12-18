package com.github.algrom13.city_info_telegram_bot.web;

import com.github.algrom13.city_info_telegram_bot.model.CityFact;
import com.github.algrom13.city_info_telegram_bot.service.CityFactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CityFactController {

    private final CityFactService cityFactService;

    @Autowired
    public CityFactController(CityFactService cityFactService) {
        this.cityFactService = cityFactService;
    }

    @GetMapping(value = "/cities/{id}/facts/{fact_id}")
    public ResponseEntity<CityFact> read(@PathVariable(name = "fact_id") Long factId) {
        CityFact cityFact = cityFactService.getFactById(factId);

        return cityFact != null ?
                new ResponseEntity<>(cityFact, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/cities/{id}/facts")
    public ResponseEntity<List<CityFact>> readAll(@PathVariable(name = "id") Long cityId) {
        List<CityFact> cityFacts = cityFactService.getFactsAboutCity(cityId);

        return cityFacts != null && !cityFacts.isEmpty() ?
                new ResponseEntity<>(cityFacts, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/cities/{city_id}/facts")
    public ResponseEntity<?> create(@PathVariable(name = "city_id") Long cityId, @RequestBody CityFact cityFact) {
        boolean isCreated = cityFactService.addFact(cityId, cityFact);
        return isCreated ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/cities/{city_id}/facts/{fact_id}")
    public ResponseEntity<?> update(@PathVariable(name = "fact_id") Long factId, @RequestBody CityFact cityFact) {
        boolean isUpdated = cityFactService.updateFact(factId, cityFact);
        return isUpdated ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/cities/{city_id}/facts/{fact_id}")
    public ResponseEntity<?> delete(@PathVariable(name = "city_id") Long cityId, @PathVariable(name = "fact_id") Long factId) {
        boolean isDeleted = cityFactService.deleteFact(cityId, factId);
        return isDeleted ?
                new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
