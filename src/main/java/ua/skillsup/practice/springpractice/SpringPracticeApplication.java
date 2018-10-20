package ua.skillsup.practice.springpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import ua.skillsup.practice.springpractice.exception.NoWeatherInfoException;
import ua.skillsup.practice.springpractice.repository.RemoteWeatherRepository;
import ua.skillsup.practice.springpractice.service.WeatherService;

@EnableScheduling
@PropertySource("classpath:/props/weather.properties")
@SpringBootApplication
public class SpringPracticeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				SpringApplication.run(SpringPracticeApplication.class, args);
		WeatherService weatherService =
				context.getBean(WeatherService.class);
		System.out.println(weatherService.currentWeather());
	}
}
