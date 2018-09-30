package ua.skillsup.practice.springpractice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.skillsup.practice.springpractice.exception.NoWeatherInfoException;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.repository.WeatherRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	private final WeatherRepository localRepository;
	private final WeatherRepository remoteRepository;
	private final String city;

	private final Map<String, WeatherData> cache = new ConcurrentHashMap<>();

	@Autowired
	public WeatherServiceImpl(@Qualifier("localRepository") WeatherRepository localRepository,
	                          @Qualifier("remoteRepository") WeatherRepository remoteRepository,
	                          @Value("${weather.city}") String city) {
		this.localRepository = localRepository;
		this.remoteRepository = remoteRepository;
		this.city = city;
	}

	@Scheduled(cron = "*/60 * * * * *")
	public void refreshCache() {
		logger.info("Schedulled task executed!");
		cache.put(city, requestWeatherData(city));
	}

	@Override
	public WeatherData currentWeather() {
		return cache.computeIfAbsent(city, this::requestWeatherData);
	}

	private WeatherData requestWeatherData(String city) {
		return localRepository.findWeatherByCity(city)
				.or(() -> remoteRepository.findWeatherByCity(city))
				.orElseThrow(() -> new NoWeatherInfoException("No weather found for " + city));
	}
}