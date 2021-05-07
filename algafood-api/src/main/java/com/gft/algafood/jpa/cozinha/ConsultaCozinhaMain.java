package com.gft.algafood.jpa.cozinha;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.gft.algafood.AlgafoodApiApplication;
import com.gft.algafood.domain.model.Cozinha;
import com.gft.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {
	public static void main(String[] args) {
		
		ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = applicationContext.getBean(CozinhaRepository.class);
		
		List<Cozinha> AllCozinhas = cozinhas.listarTodas();
		
		for (Cozinha cozinha : AllCozinhas) {
			System.out.println(cozinha.getNome());
		}
	}
}
