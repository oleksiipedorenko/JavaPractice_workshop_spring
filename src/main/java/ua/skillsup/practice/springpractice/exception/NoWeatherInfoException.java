package ua.skillsup.practice.springpractice.exception;

/**
 * Indicates that something went wrong on attempt of getting weather information
 */
public class NoWeatherInfoException extends RuntimeException {

	public NoWeatherInfoException(String message) {
		super(message);
	}

	public NoWeatherInfoException(String message, Throwable cause) {
		super(message, cause);
	}
}