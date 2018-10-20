package ua.skillsup.practice.springpractice.model.remote;

import java.util.List;

public class RemoteGroup {

	private List<City> list;

	public List<City> getList() {
		return list;
	}

	public void setList(List<City> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "RemoteGroup{" +
				"list=" + list +
				'}';
	}
}
