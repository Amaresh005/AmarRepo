package com.am.cricEver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.am"})
public class CricEverApplication {

	public static void main(String[] args) {
		SpringApplication.run(CricEverApplication.class, args);
	}

}

