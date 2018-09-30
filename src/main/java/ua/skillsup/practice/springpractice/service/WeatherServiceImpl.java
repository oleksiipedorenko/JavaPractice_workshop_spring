package ua.skillsup.practice.springpractice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ua.skillsup.practice.springpractice.exception.NoWeatherInfoException;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.repository.WeatherRepository;

@Service
public class WeatherServiceImpl implements WeatherService {

	private final WeatherRepository localRepository;
	private final WeatherRepository remoteRepository;
	private final String city;

	@Autowired
	public WeatherServiceImpl(@Qualifier("localRepository") WeatherRepository localRepository,
	                          @Qualifier("remoteRepository") WeatherRepository remoteRepository,
	                          @Value("${weather.city}") String city) {
		this.localRepository = localRepository;
		this.remoteRepository = remoteRepository;
		this.city = city;
	}

	@Override
	public WeatherData currentWeather() {
		return localRepository.findWeatherByCity(city)
				.or(() -> remoteRepository.findWeatherByCity(city))
				.orElseThrow(() -> new NoWeatherInfoException("No weather found for " + city));
	}
}