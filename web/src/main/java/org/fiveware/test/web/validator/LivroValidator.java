package org.fiveware.test.web.validator;

import org.apache.commons.lang3.StringUtils;
import org.fiveware.test.model.entity.Livro;
import org.fiveware.test.model.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class LivroValidator implements Validator {

	@Autowired
	private LivroService service;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Livro.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Livro livro = (Livro) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", 		"fiveware.book.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "editora", 	"fiveware.book.publisher.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "qtdPaginas", "fiveware.book.pages.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "isbn", 		"fiveware.book.isbn.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idioma", 	"fiveware.book.language.empty");
		
		if(StringUtils.isNoneBlank(livro.getNome())) {
			if(service.findByName(livro.getNome()) != null) {
				errors.rejectValue("nome", "fiveware.book.existent.book");
			}
		}
		
		if(livro.getCategoria() == null || livro.getCategoria().getId() == -1) {
			errors.rejectValue("categoria", "fiveware.book.category.empty");
		}
		
		if(livro.getEdicao() != null && livro.getEdicao().intValue() <= 0) {
			errors.rejectValue("edicao", "fiveware.book.invalid.edition");
		}
		
		if(livro.getQtdPaginas() != null && livro.getQtdPaginas().intValue() <= 0) {
			errors.rejectValue("qtdPaginas", "fiveware.book.invalid.pages");
		}
		
		if(livro.getPeso() != null && livro.getPeso().floatValue() <= 0) {
			errors.rejectValue("peso", "fiveware.book.invalid.weight");
		}
		
		if(livro.getIsbn() != null && livro.getIsbn().toString().length() != 13) {
			errors.rejectValue("isbn", "fiveware.book.invalid.isbn");
		}
		
	}

}
