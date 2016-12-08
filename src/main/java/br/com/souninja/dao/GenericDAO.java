package br.com.souninja.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<E, ID extends Serializable> {

	E save(E entity);

	void delete(E entity);

	List<E> findAll();

}
