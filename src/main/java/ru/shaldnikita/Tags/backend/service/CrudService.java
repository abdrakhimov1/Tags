package ru.shaldnikita.Tags.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import ru.shaldnikita.Tags.backend.model.AbstractEntity;


import java.util.Optional;


public abstract class CrudService<T extends AbstractEntity> {

	protected abstract CrudRepository<T, Long> getRepository();

	public T save(T entity) {
		return getRepository().save(entity);
	}

	public void delete(long id) {
	    T t = load(id);
	    t.setDel(true);

        save(t);
	}

	public T load(long id) {
		return getRepository().findOne(id);
	}

	public abstract long countAnyMatching(Optional<String> filter);
}
