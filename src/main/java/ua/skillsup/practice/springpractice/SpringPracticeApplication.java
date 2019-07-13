package ua.skillsup.practice.springpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import ua.skillsup.practice.springpractice.model.WeatherData;
import ua.skillsup.practice.springpractice.service.WeatherService;

@PropertySource("props/weather.service.properties")
@SpringBootApplication
public class SpringPracticeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(SpringPracticeApplication.class, args);

		WeatherService weatherServiceImpl =
				context.getBean("weatherService", WeatherService.class);

		System.out.println(weatherServiceImpl.currentWeather());
	}
}
