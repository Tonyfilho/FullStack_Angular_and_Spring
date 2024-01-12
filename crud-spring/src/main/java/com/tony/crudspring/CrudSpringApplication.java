package com.tony.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tony.crudspring.model.Course;
import com.tony.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}


	/**So para titulo, estaremos iniciando e abastecendo o banco de dados aqui, isto é errado, mas é didatico */
	@Bean
	CommandLineRunner initDataBase (CourseRepository courseRepository) {
		return args -> {
               courseRepository.deleteAll(); // limpando o que existir
			   Course c = new Course();
			   c.setName("Angular Com Spring");
			   c.setCategory("Front-end");
			   courseRepository.save(c);
		};
	}
}
