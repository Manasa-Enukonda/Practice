package com.example.demo;

import org.springframework.stereotype.Service;


@Service
public class WeatherService {


	public WeatherResponse getCityWeather(String city) {
		WeatherResponse response = new WeatherResponse();
		response.setCurrentWeather("23C");
		return response;
	}

}

	

