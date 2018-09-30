package ua.skillsup.practice.springpractice.repository.entity;

import java.util.List;

public class RemoteWeatherDetails {

	private String name;
	private RemoteWeatherMain main;
	private List<RemoteWeatherInfo> weather;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RemoteWeatherMain getMain() {
		return main;
	}

	public void setMain(RemoteWeatherMain main) {
		this.main = main;
	}

	public List<RemoteWeatherInfo> getWeather() {
		return weather;
	}

	public void setWeather(List<RemoteWeatherInfo> weather) {
		this.weather = weather;
	}
}
