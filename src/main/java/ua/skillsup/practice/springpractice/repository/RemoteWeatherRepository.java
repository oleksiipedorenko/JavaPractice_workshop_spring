package ua.skillsup.practice.springpractice.repository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.model.remote.City;
import ua.skillsup.practice.springpractice.model.remote.RemoteGroup;
import ua.skillsup.practice.springpractice.model.remote.Weather;

import java.util.Optional;
import java.util.stream.Collectors;

@Repository("remoteRepository")
public class RemoteWeatherRepository implements WeatherRepository {

	@Override
	public Optional<WeatherData> findWeatherByCity(String city) {
		if (!"London".equals(city)) {
			return Optional.empty();
		}
		RemoteGroup remoteWeather = getRemoteWeather();
		City london = remoteWeather.getList().get(0);
		return Optional.of(new WeatherData(
				london.getName(),
				london.getMain().getTemp(),
				london.getWeather().stream()
					.map(Weather::getMain)
					.collect(Collectors.toSet())
		));
	}

	private RemoteGroup getRemoteWeather() {
		String url = "https://samples.openweathermap.org/data/2.5/find?q=London&units=metric&appid=b6907d289e10d714a6e88b30761fae22";
		RestTemplate rest = new RestTemplate();
		ResponseEntity<RemoteGroup> entity =
				rest.getForEntity(url, RemoteGroup.class);
		return entity.getBody();
	}
}
