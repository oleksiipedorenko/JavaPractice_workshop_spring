package ua.skillsup.practice.springpractice.repository.entity;

import java.util.List;

public class RemoteWeatherData {

	private List<RemoteWeatherDetails> list;

	public List<RemoteWeatherDetails> getList() {
		return list;
	}

	public void setList(List<RemoteWeatherDetails> list) {
		this.list = list;
	}
}