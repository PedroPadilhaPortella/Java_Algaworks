package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas", produces = { MediaType.APPLICATION_JSON_VALUE })
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.findAll();
	}

	
	//@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		if(cozinha.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		//return ResponseEntity.status(HttpStatus.OK).body(cozinha);
		return ResponseEntity.ok(cozinha.get());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha adicionar(@Valid @RequestBody Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}
	
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @Valid @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaDb = cozinhaRepository.findById(id);
		
		if(cozinhaDb.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		BeanUtils.copyProperties(cozinha, cozinhaDb.get(), "id");
		cozinha = cozinhaService.salvar(cozinhaDb.get());
		return ResponseEntity.ok(cozinha);
	}
	
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<?> remover(@PathVariable Long id) {
		try {
			cozinhaService.remover(id);
			return ResponseEntity.noContent().build();
			
		} catch(EntidadeNaoEncontradaException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
			
		} catch(EntidadeEmUsoException exception) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
		}
	}
}
