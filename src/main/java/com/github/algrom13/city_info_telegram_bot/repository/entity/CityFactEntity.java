package com.github.algrom13.city_info_telegram_bot.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "city_fact")
public class CityFactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    @Column(name = "fact")
    private String fact;

    public CityFactEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }
}
