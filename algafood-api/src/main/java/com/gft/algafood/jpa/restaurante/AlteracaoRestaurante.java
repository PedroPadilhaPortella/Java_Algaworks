package com.gft.algafood.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.gft.algafood.AlgafoodApiApplication;
import com.gft.algafood.domain.model.Restaurante;
import com.gft.algafood.domain.repository.RestauranteRepository;

public class AlteracaoRestaurante {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		Restaurante restaurante = new Restaurante();
		restaurante.setId(3L);
		restaurante.setNome("Boi Brabo");
		
		restaurante = restaurantes.adicionar(restaurante);
		
		System.out.println(restaurante.getId() + ", " + restaurante.getNome());
	}
}
