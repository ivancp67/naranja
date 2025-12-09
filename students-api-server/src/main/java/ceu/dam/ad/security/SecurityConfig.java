package ceu.dam.ad.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;


@Configuration
@SecurityScheme(
		name = "ApiKeyAuthentication",
		type = SecuritySchemeType.APIKEY,
		in = SecuritySchemeIn.HEADER,
		paramName = "API-KEY")
public class SecurityConfig {

	@Autowired
	private ApiKeyfilter apiKeyfilter;
	
	@Bean
	SecurityFilterChain geSecurityFilterChain(HttpSecurity http) throws Exception{
		
		//Permitimos hacer peticiones POST/PUT/DELETE
		return http.csrf(c -> c.disable())
				.formLogin(f -> f.disable())
				.addFilterBefore(apiKeyfilter, UsernamePasswordAuthenticationFilter.class)
				.build();
	}
}
