package ru.shaldnikita.Tags.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.shaldnikita.Tags.firebase.FirebaseConnectionProvider;
import ru.shaldnikita.Tags.models.Tag;

import java.util.List;

/**
 * @author n.shaldenkov on 19.11.2017
 */

@RestController
@RequestMapping(value = "/{category}/tags")
public class TagsRestController {

    @Autowired
    FirebaseConnectionProvider firebaseConnectionProvider;

    @RequestMapping(method = RequestMethod.GET,value = "/{tag}")
    Tag readTag(@PathVariable String category, @PathVariable String tag) {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Tag> readTags(@PathVariable String category) {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> add(@PathVariable String category, @PathVariable Tag tag){
        return null;
    }


}
