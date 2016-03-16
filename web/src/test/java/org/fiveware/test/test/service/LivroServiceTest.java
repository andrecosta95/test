package org.fiveware.test.test.service;

import java.util.Date;

import org.fiveware.test.model.entity.Categoria;
import org.fiveware.test.model.entity.Livro;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.model.service.CategoriaService;
import org.fiveware.test.model.service.LivroService;
import org.fiveware.test.test.web.configuration.WebAppConfigTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfigTest.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LivroServiceTest {

	private static final String DEFAULT_CATEGORY_NAME = "Ação";
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private CategoriaService catService;
	
	@Before
	public void setUp() throws FivewareTestServiceException {
		if(catService.list().isEmpty()) {
			catService.add(new Categoria(null, DEFAULT_CATEGORY_NAME));
		}
	}
	
	private Livro getDefaultBook() {
		
		Livro l = new Livro();
		l.setNome("HP1");
		l.setEditora("Leya");
		l.setEdicao((short) 1);
		l.setQtdPaginas((short) 100);
		l.setPeso(50f);
		l.setIsbn(1234567891234l);
		l.setCategoria(catService.findByDescricao(DEFAULT_CATEGORY_NAME));
		l.setIdioma("pt-BR");
		l.setContemEbook(false);
		l.setDataLancamento(new Date());
		
		return l;
	}
	
	private Livro getDefaultBookToUpdate() {
		
		Livro l = new Livro();
		l.setNome("Divergente");
		l.setEditora("Rocco");
		l.setEdicao((short) 2);
		l.setQtdPaginas((short) 100);
		l.setPeso(100f);
		l.setIsbn(1234567891235l);
		l.setCategoria(catService.findByDescricao(DEFAULT_CATEGORY_NAME));
		l.setIdioma("en-US");
		l.setContemEbook(true);
		l.setDataLancamento(new Date());
		
		return l;
	}
	
	@Test
	public void addTest() throws FivewareTestServiceException {
		
		Livro l = getDefaultBook();
		
		service.add(l);
		
		Assert.assertNotNull(l.getId());
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void addTestException() throws FivewareTestServiceException {
		
		Livro l = getDefaultBook();
		
		service.add(l);
	}
	
	@Test
	public void listTest() throws FivewareTestServiceException {
		Assert.assertNotNull(service.list());
	}
	
	@Test
	public void removeTest() throws FivewareTestServiceException {

		Livro l = service.findByName(getDefaultBook().getNome());
		
		service.remove(l);
		
		Assert.assertNull(service.find(l.getId()));
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void removeTestException() throws FivewareTestServiceException {
		service.remove(getDefaultBook());
	}
	
	@Test
	public void updateTest() throws FivewareTestServiceException {

		service.add(getDefaultBook());
		
		Long id = service.findByName(getDefaultBook().getNome()).getId();
		Livro l = getDefaultBookToUpdate();
		l.setId(id);
		
		service.update(l);
		
		Livro livro = service.find(id);
		Assert.assertEquals(l.getNome(), livro.getNome());
		Assert.assertEquals(l.getEditora(), livro.getEditora());
		Assert.assertEquals(l.getEdicao(), livro.getEdicao());
		Assert.assertEquals(l.getQtdPaginas(), livro.getQtdPaginas());
		Assert.assertEquals(l.getPeso(), livro.getPeso());
		Assert.assertEquals(l.getIsbn(), livro.getIsbn());
		Assert.assertEquals(l.getCategoria().getId(), livro.getCategoria().getId());
		Assert.assertEquals(l.getIdioma(), livro.getIdioma());
		Assert.assertEquals(l.isContemEbook(), livro.isContemEbook());
		Assert.assertEquals(l.getDataLancamento(), livro.getDataLancamento());
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void updateTestNotCompleteException() throws FivewareTestServiceException {
		service.update(getDefaultBook());
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void updateTestNotFoundException() throws FivewareTestServiceException {
		
		Livro l = service.findByName(getDefaultBookToUpdate().getNome());
		l.setId(l.getId()+1);
		
		service.update(l);
	}
	
}
