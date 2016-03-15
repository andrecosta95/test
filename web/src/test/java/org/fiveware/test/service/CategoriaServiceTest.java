package org.fiveware.test.service;

import org.fiveware.test.model.entity.Categoria;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.model.service.CategoriaService;
import org.fiveware.test.web.configuration.WebAppConfigTest;
import org.junit.Assert;
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
public class CategoriaServiceTest {

	@Autowired
	private CategoriaService service;
	
	@Test
	public void addTest() throws FivewareTestServiceException {
		
		Categoria c = new Categoria(null, "Ação");
		
		service.add(c);
		
		Assert.assertNotNull(c.getId());
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void addTestException() throws FivewareTestServiceException {
		service.add(new Categoria(null, "Ação"));
	}
	
	@Test
	public void listTest() throws FivewareTestServiceException {
		Assert.assertNotNull(service.list());
	}
	
	@Test
	public void removeTest() throws FivewareTestServiceException {

		Categoria c = service.findByDescricao("Ação");
		
		service.remove(c);
		
		Assert.assertNull(service.find(c.getId()));
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void removeTestException() throws FivewareTestServiceException {
		service.remove(new Categoria(null, "Ação"));
	}
	
	@Test
	public void updateTest() throws FivewareTestServiceException {

		service.add(new Categoria(null, "Ação"));
		
		Categoria c = service.findByDescricao("Ação");
		c.setDescricao("Fantasia");
		
		service.update(c);
		
		Assert.assertEquals("Fantasia", service.find(c.getId()).getDescricao());
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void updateTestNotCompleteException() throws FivewareTestServiceException {
		service.update(new Categoria(null, "Ação"));
	}
	
	@Test(expected = FivewareTestServiceException.class)
	public void updateTestNotFoundException() throws FivewareTestServiceException {
		
		Categoria c = service.findByDescricao("Fantasia");
		c.setId(c.getId()+1);
		
		service.update(c);
	}
	
}
