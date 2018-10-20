package ua.skillsup.practice.springpractice.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.skillsup.practice.springpractice.exception.NoWeatherInfoException;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.repository.WeatherRepository;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final WeatherRepository localRepository;
	private final WeatherRepository remoteRepository;
	private final String city;

	private final ConcurrentMap<String, WeatherData> cache =
			new ConcurrentHashMap<>();

	public WeatherServiceImpl(
			@Qualifier("localRepository") WeatherRepository localRepository,
			@Qualifier("remoteRepository") WeatherRepository remoteRepository,
			@Value("${weather.city}") String city
	) {
		this.localRepository = localRepository;
		this.remoteRepository = remoteRepository;
		this.city = city;
	}

	@PostConstruct
	public void init() {
		System.out.println("Service init");
	}

	@Override
	public WeatherData currentWeather() {
		return cache.computeIfAbsent(city, s -> getWeatherData());
	}

	private WeatherData getWeatherData() {
		return remoteRepository.findWeatherByCity(city)
				.orElseGet(() ->
						localRepository.findWeatherByCity(city)
								.orElseThrow(() ->
										new NoWeatherInfoException("No weather found for " + city))
				);
	}

	@Scheduled(cron = "*/30 * * * * *")
	private void reloadCache() {
		WeatherData data = getWeatherData();
		cache.clear();
		cache.put(city, data);
		System.out.println("Cache reloaded!");
	}
}