package ru.shaldnikita.Tags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shaldnikita.Tags.backend.model.Tag;

import java.util.Collection;

public interface TagRepository extends JpaRepository<Tag,Long> {

    Collection<Tag> getAllByAuthorId(Long authorId);
    Collection<Tag> getAllByAuthorEmail(String authorEmail);
    Collection<Tag> getAllByLatitudeBetweenAndLongitudeBetweenAndDelFalse(Double startLat,Double endLat,Double startLon,Double endLon);
}
