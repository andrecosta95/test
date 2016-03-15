package org.fiveware.test.service.impl;

import java.util.List;

import org.fiveware.test.model.dao.CategoriaDAO;
import org.fiveware.test.model.entity.Categoria;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.model.service.CategoriaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CategoriaDAO dao;
	
	@Override
	public void add(Categoria cat) throws FivewareTestServiceException {
		
		try {
			
			Categoria c = dao.findByDescricao(cat.getDescricao());
			if(c == null) {
				logger.info("Persistindo categoria {}...", cat.getDescricao());
				dao.persist(cat);
			}else{
				throw new Exception("Esta categoria já está cadastrada!");
			}
			
		} catch (Exception e) {
			logger.error("Ocorreu um erro ao persistir categoria:", e);
			throw new FivewareTestServiceException(e);
		}
	}

	@Override
	public void remove(Categoria cat) throws FivewareTestServiceException {
		
		try {
			
			Categoria c = dao.findByDescricao(cat.getDescricao());
			if(c != null) {
				logger.info("Removendo categoria {}...", cat.getDescricao());
				dao.remove(cat);
			}else{
				throw new Exception("Esta categoria não está cadastrada!");
			}
			
		} catch (Exception e) {
			logger.error("Ocorreu um erro ao remover categoria:", e);
			throw new FivewareTestServiceException(e);
		}
	}

	@Override
	public void update(Categoria cat) throws FivewareTestServiceException {
		
		try {
			
			if(cat.getId() != null && cat.getDescricao() != null) {
				
				Categoria c = dao.findById(cat.getId());
				if(c != null) {
					logger.info("Atualizando categoria {} para {}...", c.getDescricao(), cat.getDescricao());
					dao.update(cat);
				}else{
					throw new Exception("Esta categoria não está cadastrada!");
				}
			}else{
				throw new Exception("A categoria a ser atualizada deve estar preenchida.");
			}
		} catch (Exception e) {
			logger.error("Ocorreu um erro ao atualizar categoria:", e);
			throw new FivewareTestServiceException(e);
		}
	}
	
	@Override
	public Categoria find(Integer key) {
		return dao.findById(key);
	}

	@Override
	public Categoria findByDescricao(String descricao) {
		return dao.findByDescricao(descricao);
	}
	
	@Override
	public List<Categoria> list() {
		return dao.findAll();
	}

}
