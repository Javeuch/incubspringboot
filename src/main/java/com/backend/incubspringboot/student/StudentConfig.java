package com.backend.incubspringboot.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student mariam = new Student(
					// on retire l'id généré par la BDD
					"mariam",
					"mariam.jamal@gmail.com",
					LocalDate.of(2000, Month.JANUARY, 5	)
					);
		
			Student alex = new Student(
					// on retire l'id généré par la BDD
					"alex",
					"alex@gmail.com",
					LocalDate.of(2004, Month.JANUARY, 5)
					);
			
			repository.saveAll(
					List.of(mariam, alex)
					);
		};
		
	}

}
