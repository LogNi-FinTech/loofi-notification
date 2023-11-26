package com.loofi.notification.processor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "com.loofi.notification.common.entities")
@EnableJpaRepositories(basePackages="com.loofi.notification.common.repositories")
@SpringBootApplication
public class ProcessorApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProcessorApplication.class, args);
	}
}
