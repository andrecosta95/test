package org.fiveware.test.model.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<E extends Serializable, K extends Serializable> {

	void persist(E entity);
	
	void update(E entity) throws Exception;
	
	void remove(E entity);
	
	E findById(K id);
	
	List<E> findAll();
	
}
