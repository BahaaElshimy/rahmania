package com.rahmania;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MosabaqaApplication  {

	public static void main(String[] args) {
		SpringApplication.run(MosabaqaApplication.class, args);
	}
}
