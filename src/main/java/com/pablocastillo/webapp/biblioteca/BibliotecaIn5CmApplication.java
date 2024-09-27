package com.pablocastillo.webapp.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pablocastillo.webapp.biblioteca.system.Main;

import javafx.application.Application;

@SpringBootApplication
public class BibliotecaIn5CmApplication {

	public static void main(String[] args) {
		//levanta JAVAFX
		Application.launch(Main.class, args);
		//levanta SpringBoot
		SpringApplication.run(BibliotecaIn5CmApplication.class, args);
	}

}
