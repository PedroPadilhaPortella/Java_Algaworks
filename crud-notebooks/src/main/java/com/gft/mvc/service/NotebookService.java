package com.gft.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.mvc.model.Notebook;
import com.gft.mvc.repository.NotebookRepository;

@Service
public class NotebookService
{
	@Autowired
	private NotebookRepository notebookRepository;
	
	public void salvar(Notebook notebook) {
		notebookRepository.save(notebook);
	}

	public void excluir(Long id) {
		notebookRepository.deleteById(id);
	}
	
	public List<Notebook> pegarTodos(){
		List<Notebook> notebooks = notebookRepository.findAll();	
		return notebooks;
	}
}
