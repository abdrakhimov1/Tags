package ru.shaldnikita.Tags.web.components;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.Label;
import org.atmosphere.config.service.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.spring.annotation.PrototypeScope;
import ru.shaldnikita.Tags.app.Application;
import ru.shaldnikita.Tags.app.HasLogger;
import ru.shaldnikita.Tags.backend.models.Category;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author n.shaldenkov on 23.11.2017
 */

@SpringComponent
@PrototypeScope
public class TagForm extends TagFormDesign implements HasLogger{


    private DatabaseReference categoriesRef;

    @Autowired
    public void setCategoriesRef(FirebaseDatabase database){
        categoriesRef = database.getReference(Application.CATEGORIES_URL);

    }

    @PostConstruct
    public void init() {
        getLogger().info("init TagForm");

        categoriesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                List<String> categories = new ArrayList<>();

                getLogger().info("{}",snapshot.getChildrenCount());

                snapshot.getChildren().forEach(dataSnapshot -> {
                    categories.add(dataSnapshot.getValue(Category.class).getName());
                });

                categorySelect.setDataProvider(new ListDataProvider<>(categories));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                getLogger().info("error");
            }
        });
    }
}
