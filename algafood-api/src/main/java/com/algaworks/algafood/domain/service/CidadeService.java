package com.algaworks.algafood.domain.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId);
		
		if (estado == null) {
			throw new EntidadeNaoEncontradaException(String.format("Estado de id %d não encontrado", estadoId));
		}
		
		cidade.setEstado(estado);
		
		return cidadeRepository.salvar(cidade);
	}
	
	public Cidade atualizar(Cidade cidade, Cidade cidadeDb) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.buscar(estadoId);
		
		if(estado == null) {
			throw new EntidadeNaoEncontradaException(String.format("Estado de id %d não encontrada", estadoId));
		}
		
		cidade.setEstado(estado);
		BeanUtils.copyProperties(cidade, cidadeDb, "id");
		
		return cidadeRepository.salvar(cidadeDb);
	}
	
	public void excluir(Long id) {
		try {
			cidadeRepository.remover(id);
			
		} catch(DataIntegrityViolationException exception) {
			throw new EntidadeEmUsoException(
					String.format("Cidade de id %d não pode ser removida pois está sendo usada "
					+ "como chave estrangeira em outras tabelas", id));
			
		} catch(EmptyResultDataAccessException exception) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade de id %d não encontrada", id));
		
		} catch(IllegalArgumentException exception) {
			throw new EntidadeNaoEncontradaException(String.format("Restaurante de id %d não encontrada", id));
		}
	}
}
