package ua.skillsup.practice.springpractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.skillsup.practice.springpractice.exception.NoWeatherInfoException;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.repository.WeatherRepository;

import java.util.Optional;

@Service("weatherService")
public class WeatherServiceImpl implements WeatherService {

	private final WeatherRepository localRepository;
	private final WeatherRepository remoteRepository;
	private final String currentCity;

	@Autowired
	public WeatherServiceImpl(@Qualifier("localRepository") WeatherRepository localRepository,
	                          @Qualifier("remoteRepository") WeatherRepository remoteRepository,
	                          @Value("${weather.data.city.current}") String city) {
		this.localRepository = localRepository;
		this.remoteRepository = remoteRepository;
		this.currentCity = city;
	}

	@Override
	public WeatherData currentWeather() {
		Optional<WeatherData> localData = localRepository.findWeatherByCity(currentCity);
		return localData.orElseGet(() -> remoteRepository.findWeatherByCity(currentCity)
				.orElseThrow(() ->
						new NoWeatherInfoException("No weather found for " + currentCity)));
	}
}