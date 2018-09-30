package ua.skillsup.practice.springpractice.service;

import ua.skillsup.practice.springpractice.exception.NoWeatherInfoException;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.repository.WeatherRepository;

public class WeatherServiceImpl implements WeatherService {

	private final WeatherRepository repository;

	public WeatherServiceImpl(WeatherRepository repository) {
		this.repository = repository;
	}

	@Override
	public WeatherData currentWeather() {
		String city = "Dnipro";
		return repository.findWeatherByCity(city)
				.orElseThrow(() -> new NoWeatherInfoException("No weather found for " + city));
	}
}