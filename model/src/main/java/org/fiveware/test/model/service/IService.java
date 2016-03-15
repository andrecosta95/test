package org.fiveware.test.model.service;

import java.io.Serializable;
import java.util.List;

import org.fiveware.test.model.exception.FivewareTestServiceException;

public interface IService<E extends Serializable, PK extends Serializable> {

	void add(E entity) throws FivewareTestServiceException;
	
	void remove(E entity) throws FivewareTestServiceException;
	
	E find(PK id);
	
	List<E> list();
}
