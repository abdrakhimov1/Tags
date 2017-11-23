package ru.shaldnikita.Tags.backend;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.atmosphere.config.service.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shaldnikita.Tags.app.Application;
import ru.shaldnikita.Tags.app.HasLogger;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

/**
 * @author n.shaldenkov on 24.11.2017
 */

@Configuration
public class DatabaseConfiguration implements HasLogger {

    @PostConstruct
    private void init() {
        getLogger().info("init database");

        try (FileInputStream serviceAccount = new FileInputStream(Application.KEY_FILE_NAME)) {

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl(Application.DATABASE_URL)
                    .build();

            FirebaseApp.initializeApp(options);

            getLogger().info("init completed");
        } catch (Exception e) {
            getLogger().info("{} \n {}", e.getMessage(), e.getCause());
        }
    }

    @Bean
    public FirebaseDatabase getDatabase(){
        return FirebaseDatabase.getInstance(FirebaseApp.getInstance());
    }

}
