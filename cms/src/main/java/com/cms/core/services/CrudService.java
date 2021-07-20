package com.cms.core.services;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;

public interface CrudService<E, K> {
	public boolean save(E entity);

	public boolean update(E entity);

	public boolean deleteById(K key);

	public boolean deleteAll();

	public List<E> findAll(Pageable pageable);

	public E findById(K key);
}
