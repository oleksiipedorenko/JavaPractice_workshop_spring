package ua.skillsup.practice.springpractice.model.remote;

public class Measurement {
	private int temp;
	private int pressure;

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	@Override
	public String toString() {
		return "Measurement{" +
				"temp=" + temp +
				", pressure=" + pressure +
				'}';
	}
}
