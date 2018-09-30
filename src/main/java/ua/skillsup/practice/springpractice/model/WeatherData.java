package ua.skillsup.practice.springpractice.model;

import java.util.Set;

public class WeatherData {

	private final String city;
	private final int temperature;
	private final Set<String> weather;

	public WeatherData(String city, int temperature, Set<String> weather) {
		this.city = city;
		this.temperature = temperature;
		this.weather = weather;
	}

	public String getCity() {
		return city;
	}

	public int getTemperature() {
		return temperature;
	}

	public Set<String> getWeather() {
		return weather;
	}

	@Override
	public String toString() {
		return "WeatherData{" +
				"city='" + city + '\'' +
				", temperature=" + temperature +
				", weather=" + weather +
				'}';
	}
}