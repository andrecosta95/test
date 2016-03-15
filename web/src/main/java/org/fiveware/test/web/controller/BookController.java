package org.fiveware.test.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.fiveware.test.model.entity.AjaxResponse;
import org.fiveware.test.model.entity.Categoria;
import org.fiveware.test.model.entity.Livro;
import org.fiveware.test.model.entity.ValidationError;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.model.service.CategoriaService;
import org.fiveware.test.model.service.LivroService;
import org.fiveware.test.web.editor.CategoriaEditor;
import org.fiveware.test.web.util.WebUtils;
import org.fiveware.test.web.validator.LivroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private LivroValidator livroValidator;
	
	private List<Categoria> categorias;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		
		binder.setValidator(livroValidator);
		
		// CONVERTE A DATA
		binder.registerCustomEditor(Date.class, "dataLancamento", new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
		binder.registerCustomEditor(Categoria.class, "categoria", new CategoriaEditor());
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		return "redirect:/books";
	}

	@ResponseBody
	@RequestMapping(value = "/books/categories", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AjaxResponse getCategories() {
		
		AjaxResponse response = new AjaxResponse();
		
		try {
			
			response.setCode(200);
			response.setResult(categoriaService.list());
			
		} catch (Exception e) {
			response.setCode(500);
			response.setMessage("An error occurred while retrieving categories, try again later.");
		}
		
		return response;
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String showAllBooks(Model model) {
		model.addAttribute("books", livroService.list());
		return "/list";
	}
	
	@RequestMapping(value = "/books/add", method = RequestMethod.GET)
	public String showAddBookForm(Model model) throws FivewareTestServiceException {
		
		model.addAttribute("bookForm", new Livro());
		populateDefaultModel(model);
		
		return "/add";
	}
	
	@ResponseBody
	@RequestMapping(value = "/books/add", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public AjaxResponse addBookAjax(@RequestBody @Validated Livro livro, BindingResult result) {
		
		AjaxResponse response = new AjaxResponse();
		
		if(!result.hasErrors()) {
			
			try {
			
				livroService.add(livro);
				
				response.setCode(200);
				response.setMessage("Book added successfully!");
				response.setResult(livro);
				
			} catch (Exception e) {
				response.setCode(500);
				response.setMessage("An error occurred during de book insertion, try again later.");
			}
		}else{
			
			List<ValidationError> errors = new ArrayList<>();
			for(ObjectError e : result.getAllErrors()) {
				
				ValidationError ve = new ValidationError();
				ve.setMessage(WebUtils.getValidationProperty(((FieldError) e).getCode()));
				ve.setField(((FieldError) e).getField());
				
				errors.add(ve);
			}
			
			response.setCode(300);
			response.setResult(errors);
		}
		
		return response;
	}
	
	private void populateDefaultModel(Model model) throws FivewareTestServiceException {
		
		categorias = categoriaService.list();
		if(CollectionUtils.isEmpty(categorias)) {
			
			Categoria c1 = new Categoria();
			c1.setDescricao("Ação");
			
			Categoria c2 = new Categoria();
			c2.setDescricao("Fantasia");
			
			categoriaService.add(c1);
			categoriaService.add(c2);
			
			categorias = categoriaService.list();
		}
		
		model.addAttribute("categories", categorias);
	}
	
}
