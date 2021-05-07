package com.gft.algafood.jpa.restaurante;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.gft.algafood.AlgafoodApiApplication;
import com.gft.algafood.domain.model.Restaurante;
import com.gft.algafood.domain.repository.RestauranteRepository;

public class InclusaoRestaurante {
public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		RestauranteRepository restaurantes = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante1 = new Restaurante();
		restaurante1.setNome("Portella Restaurante");

		Restaurante restaurante2 = new Restaurante();
		restaurante2.setNome("Marhaba Mateam");
		
		restaurante1 = restaurantes.adicionar(restaurante1);
		restaurante2 = restaurantes.adicionar(restaurante2);
		
		System.out.println(restaurante1.getId() + ", " + restaurante1.getNome());
		System.out.println(restaurante2.getId() + ", " + restaurante2.getNome());
	}
}
