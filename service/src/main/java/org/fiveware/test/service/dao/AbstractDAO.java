package org.fiveware.test.service.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.fiveware.test.model.dao.DAO;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.service.util.EntityUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractDAO<E extends Serializable, PK extends Serializable> implements DAO<E, PK> {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=FivewareTestServiceException.class)
	public void persist(E entity) {
		em.persist(entity);
		em.flush();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=FivewareTestServiceException.class)
	public void update(E entity) throws Exception {
		
		E attachedEntity = (E) em.find(entity.getClass(), EntityUtils.getEntityId(entity));
		
		EntityUtils.merge(attachedEntity, entity);
		
		em.merge(attachedEntity);
		em.flush();
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=FivewareTestServiceException.class)
	public void remove(E entity) throws Exception {
		
		E attachedEntity = findById(EntityUtils.getEntityId(entity));
		
		em.remove(attachedEntity);
		em.flush();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS)
	public E findById(PK id) {
		return (E) em.find(getEntityClass(), id);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<E> findAll() {
		
		Query query = em.createQuery("from " + getEntityClass().getSimpleName());
		List<E> result = query.getResultList();
		
		return result;
	}

	protected Class<?> getEntityClass() {
		return (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected EntityManager geEntityManager() {
		return this.em;
	}
	
}
