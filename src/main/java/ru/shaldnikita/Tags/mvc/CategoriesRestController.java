package ru.shaldnikita.Tags.mvc;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.shaldnikita.Tags.app.HasLogger;
import ru.shaldnikita.Tags.firebase.FirebaseConnectionProvider;
import ru.shaldnikita.Tags.models.Category;
import ru.shaldnikita.Tags.models.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@RestController
@RequestMapping(value = "/categories")
public class CategoriesRestController implements HasLogger {

    private DatabaseReference categoriesRef;

    @Autowired
    public void setCategoriesRef(FirebaseConnectionProvider firebaseConnectionProvider) {
        this.categoriesRef = firebaseConnectionProvider.getCategoriesRef();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{category}")
    Category readCategory(@PathVariable String category) {

        getLogger().info("GET {}", category);
        List<Category> categoryList = new ArrayList<>();

        boolean[] flag = new boolean[1];
        flag[0] = false;

        categoriesRef.child(category).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Category cat = dataSnapshot.getValue(Category.class);
                categoryList.add(cat);
                flag[0] = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                getLogger().info("no such category or error");
            }
        });

        while (!flag[0]){

        }
            return categoryList.size() > 0 ? categoryList.get(0) : null;
    }


    @RequestMapping(method = RequestMethod.GET)
    List<Category> readCategories(){

        List<Category> categories = new ArrayList<>();

        categoriesRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataSnapshot.getChildren().forEach(child ->{
                    categories.add(child.getValue(Category.class));
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                getLogger().info("cant read categories");
            }
        });

        return  categories;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Category category){

        categoriesRef.setValueAsync(category);
        return new ResponseEntity<Tag>(HttpStatus.OK);
    }
}
