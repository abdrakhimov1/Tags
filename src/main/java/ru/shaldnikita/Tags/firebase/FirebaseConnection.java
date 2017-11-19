package ru.shaldnikita.Tags.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Component;
import ru.shaldnikita.Tags.app.HasLogger;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@Component
public class FirebaseConnection implements HasLogger {
    private FirebaseOptions options;

    @PostConstruct
    private void init() {
        getLogger().info("init {}",FirebaseConnection.class.getSimpleName());

        try (FileInputStream serviceAccount = new FileInputStream("key.json")){

            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://tagsproject1.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);

            getLogger().info("init completed");
        } catch (Exception e) {
            getLogger().info("{} \n {}", e.getMessage(), e.getCause());
        }
    }

    public FirebaseApp getInstance(){
        return FirebaseApp.getInstance();
    }

}
