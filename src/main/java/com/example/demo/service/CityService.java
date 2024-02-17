package com.example.demo.service;

import com.example.demo.data.City;
import com.example.demo.repositories.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    // 1) by using findCityById() CityCustomRepository method.
//    public City findCityById (Long storyId) {
//        return cityRepository.findCityById(storyId);
//    }

    // 2) by using findById() JpaRepository method.
    public City findById (Long id) {
        return cityRepository.findById(id).orElse(null);
    }

    public List<City> getAllCities () {
        return cityRepository.findAll();
    }

    public City createCity (City city) {
        City newCity = cityRepository.saveAndFlush(city);
        if (newCity != null) {
            return newCity;
        }
        return null;
    }

    public List<City> findCityByTitle (String title) {
        List <City> citiesList = cityRepository.findCityByTitle(title);
        return citiesList;
    }

    /**
     * @Transactional: This annotation ensures that the method runs within a transaction context.
     * Spring will start and manage a transaction for the duration of the method execution,
     * allowing the update query to be executed within the transactional context.
     */
    @Transactional
    public int update(String title, String body, Long cityId) throws Exception {
        int city = cityRepository.update(title, body, cityId);
        if (city != 0){
            return city;
        }
        throw new Exception("Data Not Found");
    }
}
