package ua.skillsup.practice.springpractice.repositpry;

import ua.skillsup.practice.springpractice.model.WeatherData;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LocalWeatherRepository implements WeatherRepository {

	private final Map<String, WeatherData> localData = new HashMap<>();

	@Override
	public Optional<WeatherData> findWeatherByCity(String city) {
		return Optional.ofNullable(localData.get(city));
	}
}