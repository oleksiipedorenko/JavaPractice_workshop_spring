package ua.skillsup.practice.springpractice.dto;

import java.util.List;

public class CityWeather {

	private String name;
	private MainWeatherData main;
	private List<WeatherCondition> weather;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MainWeatherData getMain() {
		return main;
	}

	public void setMain(MainWeatherData main) {
		this.main = main;
	}

	public List<WeatherCondition> getWeather() {
		return weather;
	}

	public void setWeather(List<WeatherCondition> weather) {
		this.weather = weather;
	}
}
