package ru.shaldnikita.Tags.web.components;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.IconGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.annotation.PrototypeScope;
import ru.shaldnikita.Tags.app.Application;
import ru.shaldnikita.Tags.app.HasLogger;
import ru.shaldnikita.Tags.backend.model.dict.Category;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author n.shaldenkov on 23.11.2017
 */

@SpringComponent
@PrototypeScope
public class TagForm extends TagFormDesign implements HasLogger{

    @PostConstruct
    public void init() {
        getLogger().info("init TagForm");

        categorySelect.setDataProvider(new ListDataProvider<>(Arrays.asList(Category.getAllRoles())));

        markerSelect.setDataProvider(new ListDataProvider<>(Arrays.asList(VaadinIcons.values())));
        markerSelect.setItemIconGenerator(icon ->{
            return icon;
        });
    }
}
