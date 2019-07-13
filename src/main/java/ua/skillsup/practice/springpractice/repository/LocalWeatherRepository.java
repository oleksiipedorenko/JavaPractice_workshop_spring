package ua.skillsup.practice.springpractice.repository;

import org.springframework.stereotype.Repository;
import ua.skillsup.practice.springpractice.config.Logged;
import ua.skillsup.practice.springpractice.model.WeatherData;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository("localRepository")
public class LocalWeatherRepository implements WeatherRepository {

	private final Map<String, WeatherData> localData = new HashMap<>();

	@PostConstruct
	private void init() {
		WeatherData dniproWeather =
				new WeatherData(
						"Dnipro",
						22,
						Collections.singleton("Sunny"));
		localData.put(dniproWeather.getCity(), dniproWeather);
	}

	@Logged
	@Override
	public Optional<WeatherData> findWeatherByCity(String city) {
		return Optional.ofNullable(localData.get(city));
	}
}