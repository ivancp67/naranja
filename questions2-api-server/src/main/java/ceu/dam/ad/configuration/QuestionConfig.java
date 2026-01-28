package ceu.dam.ad.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuestionConfig {

	@Bean
	ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
}
