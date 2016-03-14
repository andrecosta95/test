package org.fiveware.test.model.service;

import org.fiveware.test.model.entity.Livro;

public interface LivroService extends IService<Livro, Long> {

	Livro findByName(String nome);
	
}
