package ua.skillsup.practice.springpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableScheduling;
import ua.skillsup.practice.springpractice.repository.RemoteWeatherRepository;
import ua.skillsup.practice.springpractice.service.WeatherService;

@PropertySources({
		@PropertySource("classpath:props/weather.properties"),
		@PropertySource("classpath:props/remote_repository.properties")
})
@SpringBootApplication
@EnableScheduling
public class SpringPracticeApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringPracticeApplication.class, args);

		WeatherService service = context.getBean(WeatherService.class);
		System.out.println(service.currentWeather());
	}
}
