package org.fiveware.test.model.service;

import java.io.Serializable;
import java.util.List;

import org.fiveware.test.model.exception.FivewareTestServiceException;

public interface IService<E extends Serializable, K extends Serializable> {

	void add(E entity) throws FivewareTestServiceException;
	
	void rem(E entity) throws FivewareTestServiceException;
	
	E find(K key);
	
	List<E> list();
}
