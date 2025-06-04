package com.example.Laboratorio_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.Laboratorio_service.micro.model.client")
public class LaboratorioServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaboratorioServiceApplication.class, args);
	}

}
