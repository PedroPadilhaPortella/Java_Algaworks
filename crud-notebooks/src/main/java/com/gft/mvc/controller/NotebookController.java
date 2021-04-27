package com.gft.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.mvc.model.Notebook;
import com.gft.mvc.service.NotebookService;

@Controller
@RequestMapping("/notebooks")
public class NotebookController {

	@Autowired
	private NotebookService notebookService;
	
	
	//TELA POST / PUT
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroNotebook");
		mv.addObject(new Notebook());
		return mv;
	}

	
	//POST
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Notebook notebook, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "CadastroNotebook";
		}
		
		notebookService.salvar(notebook);
		attributes.addFlashAttribute("mensagem", "Livro salvo com sucesso!");
		return "redirect:/notebooks";
	}

	
	//GET
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Notebook> todosNotebooks = notebookService.pegarTodos();
		ModelAndView mv = new ModelAndView("TodosNotebooks");
		mv.addObject("notebooks", todosNotebooks);
		return mv;
	}

	
	//PUT
	@RequestMapping("{id}")
	public ModelAndView editar(@PathVariable("id") Notebook notebook) {
		ModelAndView mv = new ModelAndView("EdicaoNotebook");
		mv.addObject(notebook);
		return mv;
	}
	

	//DELETE
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		notebookService.excluir(id);
		return "redirect:/notebooks";
	}
}
