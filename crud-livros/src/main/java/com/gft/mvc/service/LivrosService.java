package com.gft.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.model.Livro;
import com.gft.mvc.repository.LivroRepository;
import com.gft.mvc.repository.filter.LivroFilter;

@Service
public class LivrosService {
	
	@Autowired
	private LivroRepository livroRepository;
	
	public void salvar(Livro livro) {
		livroRepository.save(livro);
	}

	public void excluir(Long id) {
		livroRepository.deleteById(id);		
	}
	
	public List<Livro> filtrar(LivroFilter filtro){
		String titulo = filtro.getTitulo() == null ? "" : filtro.getTitulo();
		List<Livro> livros = livroRepository.findByTituloContaining(titulo);	
		return livros;
	}
}