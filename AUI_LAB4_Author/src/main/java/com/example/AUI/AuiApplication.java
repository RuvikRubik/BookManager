package com.example.AUI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.example.AUI.repository")
@ComponentScan(basePackages = { "com.example.AUI" })
@EntityScan("com.example.AUI.entity")
public class AuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuiApplication.class, args);
	}

}
