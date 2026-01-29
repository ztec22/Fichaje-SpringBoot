package com.example.fichaje;

import org.springframework.boot.SpringApplication;

public class TestFichajeApplication {

	public static void main(String[] args) {
		SpringApplication.from(FichajeApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
