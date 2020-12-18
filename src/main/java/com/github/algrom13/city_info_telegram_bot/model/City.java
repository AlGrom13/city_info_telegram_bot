package com.github.algrom13.city_info_telegram_bot.model;

import java.util.List;


public class City {

    private Long id;
    private String name;
    private List<CityFact> cityFacts;

    public City() {
    }

    public City(Long id, String name, List<CityFact> cityFacts) {
        this.id = id;
        this.name = name;
        this.cityFacts = cityFacts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CityFact> getCityFacts() {
        return cityFacts;
    }

    public void setCityFacts(List<CityFact> cityFacts) {
        this.cityFacts = cityFacts;
    }
}
