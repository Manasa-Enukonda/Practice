package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1",headers = "Accept=application/json")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    @GetMapping(value = "/weather/{cityname}")
    public ResponseEntity<WeatherResponse> getCurrentWeather(@PathVariable String cityname){
        
        WeatherResponse response = weatherService.getCityWeather(cityname);
        
        return response != null ? new ResponseEntity<>(response, HttpStatus.OK) :  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        
    }
    
}