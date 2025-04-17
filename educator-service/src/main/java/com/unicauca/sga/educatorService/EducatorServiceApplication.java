package com.unicauca.sga.educatorService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EducatorServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducatorServiceApplication.class, args);
	}

}
