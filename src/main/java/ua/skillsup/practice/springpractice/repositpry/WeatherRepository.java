package ua.skillsup.practice.springpractice.repositpry;

import ua.skillsup.practice.springpractice.model.WeatherData;

import java.util.Optional;

/**
 * Represents part of application responsible for weather data retrieving
 */
public interface WeatherRepository {

	/**
	 * Get current weather information in concrete city
	 * @param city name of city to get weather info for
	 * @return optional of weather data
	 * Could be empty in case weather was requested for unknown city
	 */
	Optional<WeatherData> findWeatherByCity(String city);
}