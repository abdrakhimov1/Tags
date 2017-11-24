package ru.shaldnikita.Tags.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shaldnikita.Tags.backend.model.Tag;

public interface TagRepository extends JpaRepository<Tag,Long> {
}
