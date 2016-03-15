package org.fiveware.test.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.fiveware.test.model.entity.Categoria;
import org.fiveware.test.model.entity.Livro;
import org.fiveware.test.model.exception.FivewareTestServiceException;
import org.fiveware.test.model.service.CategoriaService;
import org.fiveware.test.model.service.LivroService;
import org.fiveware.test.web.editor.CategoriaEditor;
import org.fiveware.test.web.validator.LivroValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LivroController {

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

	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public String showAllBooks(Model model) {
		populateListBookModel(model);
		return "/list";
	}
	
	@RequestMapping(value = "/books/add", method = RequestMethod.GET)
	public String showAddBookForm(Model model) throws FivewareTestServiceException {
		
		model.addAttribute("bookForm", new Livro());
		populateAddBookModel(model);
		
		return "/add";
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.POST)
	public String addBook(@ModelAttribute(value = "bookForm") @Validated Livro livro, 
						  BindingResult result, Model model) throws FivewareTestServiceException {
		
		if(!result.hasErrors()) {
			
			try {
			
				livroService.add(livro);
				
				model.addAttribute("css", "success");
				model.addAttribute("msg", "Book added successfully.");
				
				cleanAddBookModel(model);
				
			} catch (Exception e) {
				model.addAttribute("css", "danger");
				model.addAttribute("msg", "An error occurred while adding book, try again later.");
			}
		}else{
			populateAddBookModel(model);
		}

		return "/add";
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
	
	private void populateAddBookModel(Model model) throws FivewareTestServiceException {
		
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
	
	private void cleanAddBookModel(Model model) throws FivewareTestServiceException {
		
		Livro livro = new Livro();
		
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
		
		model.addAttribute("bookForm", livro);
		model.addAttribute("categories", categorias);
	}
	
}
