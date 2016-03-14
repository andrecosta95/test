package org.fiveware.test.service.impl;

import java.util.List;

import org.fiveware.test.model.dao.LivroDAO;
import org.fiveware.test.model.entity.Livro;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.model.service.LivroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroServiceImpl implements LivroService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private LivroDAO dao;
	
	@Override
	public void add(Livro livro) throws FivewareTestServiceException {
		
		try {
			
			Livro l = dao.findByName(livro.getNome());
			if(l == null) {
				logger.info("Persistindo livro {}...", livro.getNome());
				dao.persist(livro);
			}else{
				throw new Exception("Este livro já está cadastrado!");
			}
			
		} catch (Exception e) {
			logger.error("Ocorreu um erro ao persistir livro:", e);
			throw new FivewareTestServiceException(e);
		}
	}

	@Override
	public void rem(Livro livro) throws FivewareTestServiceException {
		
		try {
			
			Livro l = dao.findByName(livro.getNome());
			if(l != null) {
				logger.info("Removendo livro {}...", l.getNome());
				dao.remove(l);
			}else{
				throw new Exception("Este livro não está cadastrado!");
			}
			
		} catch (Exception e) {
			logger.error("Ocorreu um erro ao remover livro:", e);
			throw new FivewareTestServiceException(e);
		}
	}

	@Override
	public Livro find(Long key) {
		return dao.findById(key);
	}

	@Override
	public Livro findByName(String nome) {
		return dao.findByName(nome);
	}
	
	@Override
	public List<Livro> list() {
		return dao.findAll();
	}

}
