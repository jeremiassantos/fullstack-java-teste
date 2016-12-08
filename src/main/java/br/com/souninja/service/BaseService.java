package br.com.souninja.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<E, ID extends Serializable> {

	E save(E entity);

	void delete(E entity);

	List<E> findAll();
}
