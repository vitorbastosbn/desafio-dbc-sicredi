package com.desafio.pauta.configs;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@SuppressWarnings("unchecked")
	@Bean
	public Docket swaggerPersonApi10() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("pauta-api-1.0")
	        .useDefaultResponseMessages(false)
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("com.desafio.pauta.controllers"))
	            .paths(
	            		or(
	            		regex("/associado/v1"),
	            		regex("/pauta/v1"),
	            		regex("/sessao/v1"),
	            		regex("/voto/v1")
	            ))
	        .build()
	        .apiInfo(apiInfo());
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Docket swaggerPersonApi20() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .groupName("pauta-api-2.0")
	        .select()
	            .apis(RequestHandlerSelectors.basePackage("com.desafio.pauta.controllers"))
	            .paths(
	            		or(
	            		regex("/associado/v2"),
	            		regex("/pauta/v2"),
	            		regex("/sessao/v2"),
	            		regex("/voto/v2")
	            ))
	        .build()
	        .apiInfo(new ApiInfoBuilder().version("2.0").title("Pauta API").description("Documentação Pauta API v2.0").build());
	}
	
	

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Desafio Pauta API").description("Api do desafio técnico Sicredi.").version("1.0")
				.contact(contact()).build();
	}

	private Contact contact() {
		return new Contact("Vitor Bastos", "https://www.linkedin.com/in/vitorbastosbn/", "vitorbastosbn@gmail.com");
	}

}
