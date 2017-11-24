package ru.shaldnikita.Tags.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.vaadin.spring.events.annotation.EnableEventBus;
import ru.shaldnikita.Tags.app.security.SecurityConfig;
import ru.shaldnikita.Tags.backend.DatabaseConfiguration;
import ru.shaldnikita.Tags.backend.model.User;
import ru.shaldnikita.Tags.backend.repositories.UserRepository;
import ru.shaldnikita.Tags.backend.service.UserService;
import ru.shaldnikita.Tags.web.VaadinUI;

/**
 * @author n.shaldenkov on 23.11.2017
 */

@SpringBootApplication(scanBasePackageClasses = { VaadinUI.class, Application.class, SecurityConfig.class,
        UserService.class,ApplicationStartInit.class})
@EntityScan(basePackageClasses = User.class)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EnableEventBus
public class Application extends SpringBootServletInitializer {

	public static String DATABASE_URL = "https://tagsproject1.firebaseio.com";
	public static String KEY_FILE_NAME = "key.json";
	public static String CATEGORIES_URL = "categories";
	public static final String APP_URL = "/";
	public static final String LOGIN_URL = "/login.html";
	public static final String LOGOUT_URL = "/login.html?logout";
	public static final String LOGIN_FAILURE_URL = "/login.html?error";
	public static final String LOGIN_PROCESSING_URL = "/login";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

}
