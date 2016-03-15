package org.fiveware.test.model.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<E extends Serializable, PK extends Serializable> {

	void persist(E entity);
	
	void update(E entity) throws Exception;
	
	void remove(E entity) throws Exception;
	
	E findById(PK id);
	
	List<E> findAll();
	
}
