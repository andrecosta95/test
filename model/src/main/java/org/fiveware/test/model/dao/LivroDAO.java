package org.fiveware.test.model.dao;

import org.fiveware.test.model.entity.Livro;

public interface LivroDAO extends DAO<Livro, Long> {

	Livro findByName(String nome);
	
}
