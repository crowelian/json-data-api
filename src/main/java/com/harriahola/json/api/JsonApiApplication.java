package com.harriahola.json.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.harriahola.json.api")
public class JsonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonApiApplication.class, args);
	}

}
