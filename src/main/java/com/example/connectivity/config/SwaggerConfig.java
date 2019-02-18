package com.example.connectivity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_12).apiInfo(buildApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.connectivity.controller")).paths(PathSelectors.any())
				.build();
	}
	
	public ApiInfo buildApiInfo() {
		return new ApiInfoBuilder()
				.title("City Connectivity Service")
				.description("This service is designed to facilitate connectivity between two cities. \n\nUser can add two connected cities and check connectivity between any origin and destination cities.")
				.contact(new Contact("Japan Trivedi", "https://stackoverflow.com/users/story/944108", "japan.trivedi@hotmail.com"))
				.build();
	}
}
