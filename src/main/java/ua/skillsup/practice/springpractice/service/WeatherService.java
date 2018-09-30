package ua.skillsup.practice.springpractice.service;

import ua.skillsup.practice.springpractice.model.WeatherData;

/**
 * Main application service to get all need information about weather
 */
public interface WeatherService {

	WeatherData currentWeather();
}