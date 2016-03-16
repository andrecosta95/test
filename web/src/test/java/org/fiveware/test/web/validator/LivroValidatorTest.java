package org.fiveware.test.web.validator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.fiveware.test.model.entity.Categoria;
import org.fiveware.test.model.entity.Livro;
import org.fiveware.test.model.service.LivroService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;
import org.springframework.validation.MapBindingResult;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LivroValidatorTest {

	@InjectMocks
	private LivroValidator validator;
	
	@Mock
	private LivroService service;
	
	private Livro livroMock;
	
	@Before
	public void setUp() {
		livroMock = new Livro();
		livroMock.setNome("HP1");
		livroMock.setEditora("Leya");
		livroMock.setEdicao((short) 1);
		livroMock.setQtdPaginas((short) 100);
		livroMock.setPeso(50f);
		livroMock.setIsbn(1234567891234l);
		livroMock.setCategoria(new Categoria(1, "Ação"));
		livroMock.setIdioma("pt-BR");
		livroMock.setContemEbook(false);
		livroMock.setDataLancamento(new Date());
	}
	
	private MapBindingResult getErrorsObject(Livro livro) {
		
		Map<String, String> target = new HashMap<>();
		
		Field[] fields = Livro.class.getDeclaredFields();
		for(Field f : fields) {
			try {
				
				Method getMethod = Livro.class.getDeclaredMethod("get" + StringUtils.capitalize(f.getName()));
				Object value = getMethod.invoke(livro);
				if(value != null) {
					target.put(f.getName(), value.toString());
				}else{
					target.put(f.getName(), null);
				}
			} catch (Exception e) {}
		}
		
		return new MapBindingResult(target, Livro.class.getName());
	}
	
	@Test
	public void supportsTest() {
		Assert.assertTrue(validator.supports(Livro.class));
	}
	
	@Test
	public void validateEmptyTest() {

		Errors errors = getErrorsObject(new Livro());
		
		validator.validate(new Livro(), errors);
		
		Assert.assertEquals(7, errors.getErrorCount());
	}
	
	@Test
	public void validateInvalidTest() {

		Mockito.when(service.findByName(Mockito.anyString())).thenReturn(null);
		
		livroMock.setEdicao((short) -1);
		livroMock.setQtdPaginas((short) -1);
		livroMock.setPeso((float) -1);
		livroMock.setIsbn(1l);
		
		Errors errors = getErrorsObject(livroMock);
		
		validator.validate(livroMock, errors);
		
		Mockito.verify(service, Mockito.times(1)).findByName(Mockito.anyString());
		Assert.assertEquals(4, errors.getErrorCount());
	}
	
	@Test
	public void validateExistentTest() {

		Mockito.when(service.findByName(Mockito.anyString())).thenReturn(livroMock);
		
		Errors errors = getErrorsObject(livroMock);
		
		validator.validate(livroMock, errors);
		
		Mockito.verify(service, Mockito.times(1)).findByName(Mockito.anyString());
		Assert.assertEquals(1, errors.getErrorCount());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
