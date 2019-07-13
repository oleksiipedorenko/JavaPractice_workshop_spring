package ua.skillsup.practice.springpractice.dto;

import java.util.List;

public class RemoteWeatherData {

	private List<CityWeather> list;

	public List<CityWeather> getList() {
		return list;
	}

	public void setList(List<CityWeather> list) {
		this.list = list;
	}
}