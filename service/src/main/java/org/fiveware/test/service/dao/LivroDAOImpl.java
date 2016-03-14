package org.fiveware.test.service.dao;

import java.util.List;

import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;
import org.fiveware.test.model.dao.LivroDAO;
import org.fiveware.test.model.entity.Livro;
import org.springframework.stereotype.Repository;

@Repository
public class LivroDAOImpl extends AbstractDAO<Livro, Long> implements LivroDAO {

	@Override
	@SuppressWarnings("unchecked")
	public Livro findByName(String nome) {
		
		Query query = super.geEntityManager().createNamedQuery(Livro.SELECT_BY_NAME);
		query.setParameter(1, nome);
		
		List<Livro> livros = query.getResultList();
		if(CollectionUtils.isNotEmpty(livros)){
			return livros.get(0);
		}
		
		return null;
		
	}

}
