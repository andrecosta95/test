package org.fiveware.test.service.util;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;

public final class EntityUtils {

	private EntityUtils() {
	}
	
	@SuppressWarnings("unchecked")
	public static <E,T> T getEntityId(E entity) throws Exception {
		
		T returnedValue = null;
		Method m = entity.getClass().getDeclaredMethod("getId");
		if(m != null) {
			returnedValue = (T) m.invoke(entity);
		}
		
		return returnedValue;
	}
	
	public static <E,T> void setEntityId(E entity, T entityId) throws Exception {
		
		Method m = entity.getClass().getDeclaredMethod("setId", entityId.getClass());
		if(m != null) {
			m.invoke(entity, entityId);
		}
	}
	
	public static <E> E merge(E newEntity, E oldEntity) throws Exception {
		
		Object id = getEntityId(oldEntity);
		
		BeanUtils.copyProperties(oldEntity, newEntity);
		
		setEntityId(newEntity, id);
		
		return newEntity;
	}
	
}
