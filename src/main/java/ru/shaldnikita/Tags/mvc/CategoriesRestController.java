package ru.shaldnikita.Tags.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.shaldnikita.Tags.firebase.FirebaseConnectionProvider;
import ru.shaldnikita.Tags.models.Category;
import ru.shaldnikita.Tags.models.Tag;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@RestController
@RequestMapping(value = "/categories")
public class CategoriesRestController {

    @Autowired
    FirebaseConnectionProvider firebaseConnectionProvider;

    @RequestMapping(method = RequestMethod.GET,value = "/{category}")
    Category readCategory(@PathVariable String category){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    Category readCategories(){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@RequestBody Category category){

        firebaseConnectionProvider.getCategoriesRef().setValueAsync(new Tag("test"));
        return null;
    }
}
