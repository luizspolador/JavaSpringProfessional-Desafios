package com.devsuperior.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Enconde: " + passwordEncoder.encode("123456"));

		boolean result = passwordEncoder.matches("123456", "$2a$10$JKgLJiyDuYtNrPnEFmA3VekextEZmpuonw8EeUEUe07usvv4kTjbG");
		System.out.println("RESULT: " + result); // true
	}
}
