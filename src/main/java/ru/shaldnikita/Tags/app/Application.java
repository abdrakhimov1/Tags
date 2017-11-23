package ru.shaldnikita.Tags.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.vaadin.spring.events.annotation.EnableEventBus;
import ru.shaldnikita.Tags.backend.DatabaseConfiguration;
import ru.shaldnikita.Tags.web.VaadinUI;

/**
 * @author n.shaldenkov on 23.11.2017
 */

@SpringBootApplication(scanBasePackageClasses = { VaadinUI.class, Application.class, DatabaseConfiguration.class})
@EnableEventBus
public class Application extends SpringBootServletInitializer {

	public static String DATABASE_URL = "https://tagsproject1.firebaseio.com";
	public static String KEY_FILE_NAME = "key.json";
	public static String CATEGORIES_URL = "categories";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
