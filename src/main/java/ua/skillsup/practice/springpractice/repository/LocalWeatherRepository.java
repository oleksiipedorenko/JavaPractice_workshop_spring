package ua.skillsup.practice.springpractice.repository;

import org.springframework.stereotype.Repository;
import ua.skillsup.practice.springpractice.model.WeatherData;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository("localRepository")
public class LocalWeatherRepository implements WeatherRepository {

	private final Map<String, WeatherData> localData = new HashMap<>();

	@PostConstruct
	public void init() {
		localData.put("Dnipro", new WeatherData("Dnipro", 16, Collections.singleton("Sunny")));
	}

	@Override
	public Optional<WeatherData> findWeatherByCity(String city) {
		return Optional.ofNullable(localData.get(city));
	}
}