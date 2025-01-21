package com.customersphere;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.customersphere")
public class CustomersphereApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomersphereApplication.class, args);
	}

}
