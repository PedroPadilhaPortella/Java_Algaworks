package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.salvar(cozinha);
	}
	
	
	public void remover(Long id) {
		try {
			cozinhaRepository.remover(id);
			
		} catch(DataIntegrityViolationException exception) {
			throw new EntidadeEmUsoException(
				String.format("Cozinha de id %d não pode ser removida pois está sendo usada "
				+ "como chave estrangeira em outras tabelas", id));
			
		} catch(EmptyResultDataAccessException exception) {
			throw new EntidadeNaoEncontradaException(String.format("Cozinha de id %d não encontrada", id));
		}
	}
	
}
