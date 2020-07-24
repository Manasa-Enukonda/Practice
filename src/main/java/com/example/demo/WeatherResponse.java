package com.example.demo;


public class WeatherResponse {

	private String currentWeather;

	public WeatherResponse(String currentWeather) {
		this.currentWeather = currentWeather;
	}

	public String getCurrentWeather() {
		return currentWeather;
	}

	public void setCurrentWeather(String currentWeather) {
		this.currentWeather = currentWeather;
	}
}
