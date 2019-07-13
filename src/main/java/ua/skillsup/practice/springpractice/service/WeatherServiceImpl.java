package ua.skillsup.practice.springpractice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ua.skillsup.practice.springpractice.config.Logged;
import ua.skillsup.practice.springpractice.exception.NoWeatherInfoException;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.repository.WeatherRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {

	private final Logger logger =
			LoggerFactory.getLogger(WeatherServiceImpl.class);

	private final WeatherRepository localRepository;
	private final WeatherRepository remoteRepository;
	private final String currentCity;
	private final Map<String, WeatherData> cache =
			new ConcurrentHashMap<>();

	@Autowired
	public WeatherServiceImpl(@Qualifier("localRepository") WeatherRepository localRepository,
	                          @Qualifier("remoteRepository") WeatherRepository remoteRepository,
	                          @Value("${weather.data.city.current}") String city) {
		this.localRepository = localRepository;
		this.remoteRepository = remoteRepository;
		this.currentCity = city;
	}

	@Scheduled(cron = "*/30 * * * * *")
	private void updateCache() {
		logger.info("Update cache!");
		cache.put("Dnipro", getWeather("Dnipro"));
		cache.put("London", getWeather("London"));
	}

	private WeatherData getWeather(String city) {
		Optional<WeatherData> localData = localRepository.findWeatherByCity(city);
		return localData.orElseGet(() -> remoteRepository.findWeatherByCity(city)
				.orElseThrow(() ->
						new NoWeatherInfoException("No weather found for " + city)));
	}

	@Override
	public WeatherData currentWeather() {
		return Optional.ofNullable(cache.get(currentCity))
				.orElseThrow(() ->
						new NoWeatherInfoException("No weather found for " + currentCity));
	}
}