package com.algaworks.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algamoneyapi.model.Pessoa;
import com.algaworks.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		Pessoa pessoaDB = buscarPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaDB, "codigo");
		return pessoaRepository.save(pessoaDB);
	}

	
	public void atualizarAtivo(Long codigo, boolean ativo) {
		Pessoa pessoaDB = buscarPeloCodigo(codigo);
		pessoaDB.setAtivo(ativo);
		pessoaRepository.save(pessoaDB);
	}
	
	
	public Pessoa buscarPeloCodigo(Long codigo) {
		Pessoa pessoaDB = pessoaRepository.findById(codigo).orElse(null);
			
		if(pessoaDB == null) throw new EmptyResultDataAccessException(1);
		return pessoaDB;
	}
}
