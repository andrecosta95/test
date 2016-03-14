package org.fiveware.test.model.dao;

import org.fiveware.test.model.entity.Categoria;

public interface CategoriaDAO extends DAO<Categoria, Integer> {

	Categoria findByDescricao(String descricao);
	
}
