package com.project.Atividade.Backend.Framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class AtividadeBackendFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtividadeBackendFrameworkApplication.class, args);
	}

}
