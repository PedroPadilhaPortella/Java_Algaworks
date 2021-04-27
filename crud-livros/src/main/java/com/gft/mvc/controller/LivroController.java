package com.gft.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.mvc.model.Livro;
import com.gft.mvc.repository.filter.LivroFilter;
import com.gft.mvc.service.LivrosService;

@Controller
@RequestMapping("/livros")
public class LivroController {

	//private static final String CADASTRO_VIEW = "CadastroLivro";

	@Autowired
	private LivrosService livroService;
	
	
	//POST
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroLivro");
		mv.addObject(new Livro());
		return mv;
	}

	
	//POST
	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Livro livro, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return "CadastroLivro";
		}
		
		livroService.salvar(livro);
		attributes.addFlashAttribute("mensagem", "Livro salvo com sucesso!");
		return "redirect:/livros";
	}

	
	//GET
	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro")LivroFilter filtro) {
		List<Livro> todosLivros = livroService.filtrar(filtro);
		ModelAndView mv = new ModelAndView("PesquisaLivros");
		mv.addObject("livros", todosLivros);
		return mv;
	}

	
	//PUT
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Livro livro) {
		ModelAndView mv = new ModelAndView("EdicaoLivro");
		mv.addObject(livro);
		return mv;
	}
	

	//DELETE
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {
		livroService.excluir(id);
		attributes.addFlashAttribute("mensagem", "Livro excluído com sucesso!");
		return "redirect:/livros";
	}
}
