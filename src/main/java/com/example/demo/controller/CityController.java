package com.example.demo.controller;

import com.example.demo.data.City;
import com.example.demo.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rest/city")
public class CityController {

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<City>> getAllCity () throws Exception {

        List<City> citiesList = cityService.getAllCities();
        if (citiesList != null) {
            return ResponseEntity.ok(citiesList);
        }
        throw new Exception("No Data Found !!");
    }

    @PostMapping(value = "/addCity")
    public ResponseEntity<City> addNewCity (@RequestBody City city) throws Exception {
        try {
            City newCity = cityService.createCity(city);
            return ResponseEntity.ok(newCity );
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("get All not Found");
        }
    }

    @GetMapping(value = "/get-city")
    public ResponseEntity<List<City>> findCityByTitle (@RequestParam("title") String title) throws Exception {
        try{
            List<City> citiesList = cityService.findCityByTitle(title);
            if (citiesList != null) {
                return ResponseEntity.ok(citiesList);
            }
            throw new Exception("No Data Found");
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No Data Fond");
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Integer> update(@RequestBody City city, @PathVariable Long id) throws Exception {
        int updateCity = cityService.update(city.getTitle(), city.getBody(), id);

        return ResponseEntity.ok(updateCity);
    }
}
