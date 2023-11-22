package com.example.backend_integracao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "com.example.backend_integracao.repository")
public class BackendIntegracaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendIntegracaoApplication.class, args);
	}

}
