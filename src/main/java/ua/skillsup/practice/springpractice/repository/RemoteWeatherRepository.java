package ua.skillsup.practice.springpractice.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.repository.entity.RemoteWeatherData;
import ua.skillsup.practice.springpractice.repository.entity.RemoteWeatherDetails;
import ua.skillsup.practice.springpractice.repository.entity.RemoteWeatherInfo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository("remoteRepository")
public class RemoteWeatherRepository implements WeatherRepository {

	private final List<String> allowedCities;
	private final String url;

	public RemoteWeatherRepository(@Value("${remote.weather.repository}") String url,
	                               @Value("${remote.weather.cities}") List<String> availableCities) {
		this.allowedCities = availableCities;
		this.url = url;
	}

	@Override
	public Optional<WeatherData> findWeatherByCity(String city) {
		if (allowedCities.contains(city)) {

			RestTemplate rest = new RestTemplate();
			ResponseEntity<RemoteWeatherData> entity =
					rest.getForEntity(url, RemoteWeatherData.class);

			return Optional.ofNullable(entity.getBody())
					.map(RemoteWeatherData::getList)
					.filter(list -> !list.isEmpty())
					.map(list -> list.get(0))
					.map(RemoteWeatherRepository::toModel);
		} else {
			return Optional.empty();
		}
	}

	private static WeatherData toModel(RemoteWeatherDetails weatherDetails) {
		Set<String> weather = weatherDetails.getWeather().stream()
				.map(RemoteWeatherInfo::getMain)
				.collect(Collectors.toSet());
		return new WeatherData(weatherDetails.getName(), weatherDetails.getMain().getTemp(), weather);
	}
}