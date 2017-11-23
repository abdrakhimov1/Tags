package ru.shaldnikita.Tags.backend.firebase;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.shaldnikita.Tags.app.HasLogger;

import javax.annotation.PostConstruct;

/**
 * @author n.shaldenkov on 19.11.2017
 */
@Component
@Scope("prototype")
public class FirebaseConnectionProvider extends FirebaseConnection implements HasLogger{

    private FirebaseApp app;
    private FirebaseDatabase database;

    private DatabaseReference categoriesRef;

    @PostConstruct
    public void init(){
        getLogger().info("init {}",FirebaseConnectionProvider.class.getSimpleName());

        app = FirebaseApp.getInstance();
        database = FirebaseDatabase.getInstance(app);

        categoriesRef = database.getReference("categories");
    }

    public DatabaseReference getCategoriesRef() {
        return categoriesRef;
    }
}
