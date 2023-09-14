package com.api.ninebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NineboxApplication {

	public static void main(String[] args) {
		SpringApplication.run(NineboxApplication.class, args);
		System.out.println("NineBox App Initiated.");
	}
}
