package com.gft.algafood.jpa.restaurante;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.gft.algafood.AlgafoodApiApplication;
import com.gft.algafood.domain.model.Restaurante;
import com.gft.algafood.domain.repository.RestauranteRepository;

public class ConsultaRestaurante {
	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> AllRestaurantes = restaurantes.listarTodos();
		
		for (Restaurante restaurante : AllRestaurantes) {
			System.out.println(restaurante.getNome());
		}
	}
}
