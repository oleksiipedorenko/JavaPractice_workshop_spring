package ua.skillsup.practice.springpractice.model.remote;

import java.util.List;

public class City {
	private String name;
	private Measurement main;
	private List<Weather> weather;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Measurement getMain() {
		return main;
	}

	public void setMain(Measurement main) {
		this.main = main;
	}

	public List<Weather> getWeather() {
		return weather;
	}

	public void setWeather(List<Weather> weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return "City{" +
				"name='" + name + '\'' +
				", main=" + main +
				", weather=" + weather +
				'}';
	}
}
