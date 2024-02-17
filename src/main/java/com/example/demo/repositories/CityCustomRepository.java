package com.example.demo.repositories;

import com.example.demo.data.City;

import java.util.List;

public interface CityCustomRepository {

    public City findCityById(Long cityId);
    public List<City> getAllCities();
    public boolean createCity(City city);
}
