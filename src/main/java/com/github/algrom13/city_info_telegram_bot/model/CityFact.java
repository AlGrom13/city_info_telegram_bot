package com.github.algrom13.city_info_telegram_bot.model;

public class CityFact {

    private Long id;
    private String fact;
    private Long cityId;

    public CityFact() {
    }

    public CityFact(Long id, String fact, Long cityId) {
        this.id = id;
        this.fact = fact;
        this.cityId = cityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
}
