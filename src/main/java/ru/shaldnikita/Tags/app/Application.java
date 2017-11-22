package ru.shaldnikita.Tags.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.vaadin.spring.events.annotation.EnableEventBus;
import ru.shaldnikita.Tags.web.VaadinUI;


@SpringBootApplication(scanBasePackageClasses = { VaadinUI.class, Application.class })
@EnableEventBus
public class Application extends SpringBootServletInitializer {

	public static String DATABASE_URL = "https://tagsproject1.firebaseio.com";
	public static String KEY_FILE_NAME = "key.json";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}