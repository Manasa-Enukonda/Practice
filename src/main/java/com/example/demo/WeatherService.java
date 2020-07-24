package com.example.demo;

import org.springframework.stereotype.Service;


@Service
public class WeatherService {


	public WeatherResponse getCityWeather(String city) {
		WeatherResponse response = new WeatherResponse("23C");
		return response;
	}

}

	

