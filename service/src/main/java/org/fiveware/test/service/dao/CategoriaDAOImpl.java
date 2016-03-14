package org.fiveware.test.service.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.fiveware.test.model.dao.CategoriaDAO;
import org.fiveware.test.model.entity.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriaDAOImpl extends AbstractDAO<Categoria, Integer> implements CategoriaDAO {

	@Override
	@SuppressWarnings("unchecked")
	public Categoria findByDescricao(String descricao) {
		
		Query query = super.geEntityManager().createNamedQuery(Categoria.SELECT_BY_NAME);
		query.setParameter(1, descricao);
		
		List<Categoria> categorias = query.getResultList();
		if(CollectionUtils.isNotEmpty(categorias)){
			return categorias.get(0);
		}
		
		return null;
	}

}
