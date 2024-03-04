package com.example.backendphotobook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BackendPhotobookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendPhotobookApplication.class, args);
	}

}
