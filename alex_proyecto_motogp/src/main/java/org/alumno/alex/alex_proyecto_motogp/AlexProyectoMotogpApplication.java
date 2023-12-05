package org.alumno.alex.alex_proyecto_motogp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "org.alumno.alex")
public class AlexProyectoMotogpApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlexProyectoMotogpApplication.class, args);
	}

}
