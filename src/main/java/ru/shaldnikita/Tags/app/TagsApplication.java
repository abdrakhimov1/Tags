package ru.shaldnikita.Tags.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.shaldnikita.Tags.firebase.FirebaseConnection;
import ru.shaldnikita.Tags.mvc.CategoriesRestController;

@SpringBootApplication
@ComponentScan(basePackageClasses = {FirebaseConnection.class, CategoriesRestController.class})
public class TagsApplication {

	public static String DATABASE_URL = "https://tagsproject1.firebaseio.com";
	public static String KEY_FILE_NAME = "key.json";

	public static void main(String[] args) {
		SpringApplication.run(TagsApplication.class, args);
	}

}
