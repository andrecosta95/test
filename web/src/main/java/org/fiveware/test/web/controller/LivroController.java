package org.fiveware.test.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LivroController {

	@Autowired
	private CategoriaService categoriaService;
	
	@Autowired
	private LivroService livroService;
	
	@Autowired
	private LivroValidator livroValidator;
	
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

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String showAllBooks(Model model) {
		populateListBookModel(model);
		return "/list";
	}
	
	@RequestMapping(value = "/books/add", method = RequestMethod.GET)
	public String showAddBookForm(Model model) throws FivewareTestServiceException {
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
	
	@RequestMapping(value = "/books/{id}/delete", method = RequestMethod.POST)
	public String deleteBook(@PathVariable("id") long id, final RedirectAttributes redirectAttributes) throws FivewareTestServiceException {
		
		try {
			
			Livro l = livroService.find(id);
			if(l != null) {
				livroService.remove(l);
			}
			
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "Book deleted successfully.");
			
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("css", "danger");
			redirectAttributes.addFlashAttribute("msg", "An error occurred while deleting book, try again later.");
		}
		
		return "redirect:/books";
	}
	
	private void populateListBookModel(Model model) {
		model.addAttribute("books", livroService.list());
	}

}
