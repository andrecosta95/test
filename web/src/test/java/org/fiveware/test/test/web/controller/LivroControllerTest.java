package org.fiveware.test.test.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.fiveware.test.model.entity.AjaxResponse;
import org.fiveware.test.model.entity.Categoria;
import org.fiveware.test.model.entity.Livro;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.model.service.CategoriaService;
import org.fiveware.test.model.service.LivroService;
import org.fiveware.test.test.web.configuration.WebAppConfigTest;
import org.fiveware.test.test.web.util.TestBindingResult;
import org.fiveware.test.web.controller.LivroController;
import org.fiveware.test.web.validator.LivroValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebAppConfigTest.class})
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LivroControllerTest {

	@InjectMocks
	private LivroController controller;
	
	@Mock
	private CategoriaService categoriaService;
	
	@Mock
	private LivroService livroService;
	
	@Mock
	private LivroValidator livroValidator;
	
	private List<Livro> livrosMock = new ArrayList<>();
	
	private List<Categoria> categoriasMock = new ArrayList<>();
	
	@Before
	public void setUp() {
		
		MockitoAnnotations.initMocks(this);
		
		Livro l = new Livro();
		l.setNome("HP1");
		l.setEditora("Leya");
		l.setEdicao((short) 1);
		l.setQtdPaginas((short) 100);
		l.setPeso(50f);
		l.setIsbn(1234567891234l);
		l.setCategoria(new Categoria(1, "Ação"));
		l.setIdioma("pt-BR");
		l.setContemEbook(false);
		l.setDataLancamento(new Date());
		
		livrosMock.add(l);
		
		categoriasMock.add(new Categoria(1, "Ação"));
		categoriasMock.add(new Categoria(2, "Romance"));
	}
	
	@Test
	public void indexTest() {
		Assert.assertEquals("redirect:/books", controller.index(null));
	}
	
	@Test
	public void showAllBooksTest() {
		
		Mockito.when(livroService.list()).thenReturn(livrosMock);
		
		Model m = new ExtendedModelMap();
		Assert.assertEquals("/list", controller.showAllBooks(m));
		
		Mockito.verify(livroService, Mockito.times(1)).list();
		Assert.assertEquals(1, m.asMap().size());
	}
	
	@Test
	public void showAddBookFormWithoutCategories() throws FivewareTestServiceException {
		
		Mockito.when(categoriaService.list()).thenReturn(new ArrayList<>());
		Mockito.doNothing().when(categoriaService).add(Mockito.any(Categoria.class));
		
		Assert.assertEquals("/add", controller.showAddBookForm(null));
		
		Mockito.verify(categoriaService, Mockito.times(1)).list();
		Mockito.verify(categoriaService, Mockito.times(3)).add(Mockito.any(Categoria.class));
	}
	
	@Test
	public void showAddBookFormWithCategories() throws FivewareTestServiceException {
		
		Mockito.when(categoriaService.list()).thenReturn(categoriasMock);
		
		Assert.assertEquals("/add", controller.showAddBookForm(null));
		
		Mockito.verify(categoriaService, Mockito.times(1)).list();
		Mockito.verify(categoriaService, Mockito.times(0)).add(Mockito.any(Categoria.class));
	}
	
	@Test
	public void getCategoriesAjaxTest() {
		
		Mockito.when(categoriaService.list()).thenReturn(categoriasMock);
		
		AjaxResponse response = controller.getCategoriesAjax();
		
		Mockito.verify(categoriaService, Mockito.times(1)).list();
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getCode());
	}
	
	@Test
	public void getCategoriesAjaxExceptionTest() {
		
		Mockito.doThrow(Exception.class).when(categoriaService).list();
		
		AjaxResponse response = controller.getCategoriesAjax();
		
		Mockito.verify(categoriaService, Mockito.times(1)).list();
		Assert.assertNotNull(response);
		Assert.assertEquals(500, response.getCode());
	}
	
	@Test
	public void getBookAjaxTest() {
		
		Mockito.when(livroService.find(Mockito.anyLong())).thenReturn(livrosMock.get(0));
		
		AjaxResponse response = controller.getBookAjax(1l);
		
		Mockito.verify(livroService, Mockito.times(1)).find(Mockito.anyLong());
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getCode());
	}
	
	@Test
	public void getBookAjaxTestException() {
		
		Mockito.doThrow(Exception.class).when(livroService).find(Mockito.anyLong());
		
		AjaxResponse response = controller.getBookAjax(1l);
		
		Mockito.verify(livroService, Mockito.times(1)).find(Mockito.anyLong());
		Assert.assertNotNull(response);
		Assert.assertEquals(500, response.getCode());
	}
	
	@Test
	public void addBookAjaxTest() throws FivewareTestServiceException {
		
		BindingResult result = new TestBindingResult("", false);
		Mockito.doNothing().when(livroService).add(Mockito.any(Livro.class));
		
		AjaxResponse response = controller.addBookAjax(null, result);
		
		Mockito.verify(livroService, Mockito.times(1)).add(Mockito.any(Livro.class));
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getCode());
	}
	
	@Test
	public void addBookAjaxWithValidationErrorsTest() throws FivewareTestServiceException {
		
		BindingResult result = new TestBindingResult("", true);
		
		AjaxResponse response = controller.addBookAjax(null, result);
		
		Assert.assertNotNull(response);
		Assert.assertEquals(300, response.getCode());
	}
	
	@Test
	public void addBookAjaxExceptionTest() throws FivewareTestServiceException {
		
		BindingResult result = new TestBindingResult("", false);
		Mockito.doThrow(Exception.class).when(livroService).add(Mockito.any(Livro.class));
		
		AjaxResponse response = controller.addBookAjax(null, result);
		
		Mockito.verify(livroService, Mockito.times(1)).add(Mockito.any(Livro.class));
		Assert.assertNotNull(response);
		Assert.assertEquals(500, response.getCode());
	}
	
	@Test
	public void deleteBookAjaxTest() throws FivewareTestServiceException {
		
		Mockito.when(livroService.find(Mockito.anyLong())).thenReturn(livrosMock.get(0));
		Mockito.doNothing().when(livroService).remove(Mockito.any(Livro.class));
		
		AjaxResponse response = controller.deleteBookAjax(1l);
		
		Mockito.verify(livroService, Mockito.times(1)).find(Mockito.anyLong());
		Mockito.verify(livroService, Mockito.times(1)).remove(Mockito.any(Livro.class));
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getCode());
	}
	
	@Test
	public void deleteBookDeletedAjaxTest() throws FivewareTestServiceException {
		
		Mockito.when(livroService.find(Mockito.anyLong())).thenReturn(null);
		
		AjaxResponse response = controller.deleteBookAjax(1l);
		
		Mockito.verify(livroService, Mockito.times(1)).find(Mockito.anyLong());
		Mockito.verify(livroService, Mockito.times(0)).remove(Mockito.any(Livro.class));
		Assert.assertNotNull(response);
		Assert.assertEquals(200, response.getCode());
	}
	
	@Test
	public void deleteBookAjaxExceptionTest() throws FivewareTestServiceException {
		
		Mockito.doThrow(Exception.class).when(livroService).find(Mockito.anyLong());
		
		AjaxResponse response = controller.deleteBookAjax(1l);
		
		Mockito.verify(livroService, Mockito.times(1)).find(Mockito.anyLong());
		Assert.assertNotNull(response);
		Assert.assertEquals(500, response.getCode());
	}
	
}
