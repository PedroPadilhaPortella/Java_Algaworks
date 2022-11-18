package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@RestController
public class TestesController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	
	//Restaurantes
	
	@GetMapping(value = "restaurantes/frete-entre")
	public List<Restaurante> listarPorTaxa(BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping(value = "restaurantes/nome-e-cozinha")
	public List<Restaurante> listarPorNomeDeCozinha(String nome, Long id) {
		//return restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);
		return restauranteRepository.consultarPorNome(nome, id);
	}
	
	@GetMapping(value = "restaurantes/primeiro-por-nome")
	public Optional<Restaurante> listarPorPrimeiroPorNome(String nome) {
		return restauranteRepository.findFirstByNomeContaining(nome);
	}
	
	@GetMapping(value = "restaurantes/top2-por-nome")
	public List<Restaurante> findTop2ByNomeContaining(String nome) {
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}

	@GetMapping(value = "restaurantes/count")
	public int quantidadeDeRestaurantesPorCozinha(Long cozinha) {
		return restauranteRepository.countByCozinhaId(cozinha);
	}
	
	
	//Cozinhas
	
	@GetMapping(value = "cozinhas/nome")
	public List<Cozinha> consultar(@RequestParam("nome") String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	@GetMapping(value = "cozinhas/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasWrapper listarXml() {
		return new CozinhasWrapper(cozinhaRepository.findAll());
	}
	
	@GetMapping(value = "cozinhas/existe")
	public boolean existePorNome(String nome) {
		return cozinhaRepository.existsByNome(nome);
	}
	
}
