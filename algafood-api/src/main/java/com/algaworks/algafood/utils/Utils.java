package com.algaworks.algafood.utils;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.util.ReflectionUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {


	public static Restaurante merge(Map<String, Object> campos, Restaurante restauranteTarget) {
		ObjectMapper objectMapper = new ObjectMapper();
		
		Restaurante restauranteSource = objectMapper.convertValue(campos, Restaurante.class);
		
		campos.forEach((prop, value) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, prop);
			field.setAccessible(true);
			
			Object updatedValue = ReflectionUtils.getField(field, restauranteSource);
			
			ReflectionUtils.setField(field, restauranteTarget, updatedValue);
		});
		
		return restauranteTarget;
	}
}
