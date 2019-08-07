package com.example.dwp;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicate;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class DwpApplication {

	public static void main(String[] args) {
		SpringApplication.run(DwpApplication.class, args);
	}

	@SuppressWarnings("unchecked")
	@Bean
	public Docket swaggerSpringMvcPlugin() {
		Predicate<String> apiPaths = or(regex("/cityOrDistance.*"));
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.paths(apiPaths)
			.build();
	}
}
