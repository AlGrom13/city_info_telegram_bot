package com.github.algrom13.city_info_telegram_bot.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "city_name", unique = true)
    private String name;

    @OneToMany(mappedBy = "cityEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<CityFactEntity> cityFactEntityList;

    public CityEntity() {
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

    public List<CityFactEntity> getCityFactEntityList() {
        return cityFactEntityList;
    }

    public void setCityFactEntityList(List<CityFactEntity> cityFactEntityList) {
        this.cityFactEntityList = cityFactEntityList;
    }
}
