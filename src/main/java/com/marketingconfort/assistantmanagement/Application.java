package com.marketingconfort.assistantmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@ComponentScan(basePackages = {
		"com.marketingconfort.starter.core",
		"com.marketingconfort.assistantmanagement.*"
})
@EnableJpaRepositories(basePackages = {
		"com.marketingconfort.starter.core.models",
		"com.marketingconfort.assistantmanagement.repository"
})
@EntityScan(basePackages = {
		"com.marketingconfort.starter.core",
		"com.marketingconfort.brainboost_common.assistantmanagement.models",
		"com.marketingconfort.brainboost_common.assistant_devoir.models"  // AJOUTER CETTE LIGNE
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}