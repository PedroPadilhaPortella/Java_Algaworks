package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa updatePessoa(Long id, Pessoa pessoa) {
		Pessoa pessoaDB = getPessoaById(id);
		BeanUtils.copyProperties(pessoa, pessoaDB, "id");
		return pessoaRepository.save(pessoaDB);
	}

	
	public void updateStatus(Long id, Boolean status) {
		Pessoa pessoaDB = getPessoaById(id);
		pessoaDB.setAtivo(status);
		pessoaRepository.save(pessoaDB);
	}
	
	public Pessoa getPessoaById(Long id) {
		Pessoa pessoaDB = pessoaRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaDB;
	}
}
