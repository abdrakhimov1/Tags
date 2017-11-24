package ru.shaldnikita.Tags.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.shaldnikita.Tags.backend.model.Tag;
import ru.shaldnikita.Tags.backend.repositories.TagRepository;

import java.util.Optional;

@Service
public class TagService extends CrudService<Tag> {

    @Autowired
    private TagRepository tagRepository;

    @Override
    protected TagRepository getRepository() {
        return tagRepository;
    }

    @Override
    public long countAnyMatching(Optional<String> filter) {
        return 0;
    }
}
