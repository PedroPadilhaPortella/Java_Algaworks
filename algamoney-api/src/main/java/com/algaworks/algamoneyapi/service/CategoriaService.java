package com.algaworks.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.model.Categoria;
import com.algaworks.algamoneyapi.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaDB = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(categoria, categoriaDB, "codigo");
		return categoriaRepository.save(categoria);
	}
	
	
	private Categoria buscarPeloCodigo(Long codigo) {
		Categoria pessoaDB = categoriaRepository.findById(codigo).orElse(null);
			
		if(pessoaDB == null) throw new EmptyResultDataAccessException(1);
		return pessoaDB;
	}
}
