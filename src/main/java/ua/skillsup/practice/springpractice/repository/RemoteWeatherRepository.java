package ua.skillsup.practice.springpractice.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ua.skillsup.practice.springpractice.dto.RemoteWeatherData;
import ua.skillsup.practice.springpractice.dto.WeatherCondition;
import ua.skillsup.practice.springpractice.model.WeatherData;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository("remoteRepository")
public class RemoteWeatherRepository implements WeatherRepository {

	private String url;

	public RemoteWeatherRepository(@Value("${weather.data.remote.url}") String url) {
		this.url = url;
	}

	@Override
	public Optional<WeatherData> findWeatherByCity(String city) {
		RestTemplate rest = new RestTemplate();
		ResponseEntity<RemoteWeatherData> response =
				rest.getForEntity(url, RemoteWeatherData.class);

		return Optional.ofNullable(response.getBody())
				.filter(remote -> remote.getList().size() == 1)
				.map(remote -> remote.getList().get(0))
				.filter(cityWeather -> cityWeather.getName().equals(city))
				.map(cityWeather -> new WeatherData(
						cityWeather.getName(),
						cityWeather.getMain().getTemp(),
						cityWeather.getWeather().stream()
								.map(WeatherCondition::getMain)
								.collect(Collectors.toSet())
				));
	}
}
