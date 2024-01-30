package com.tony.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tony.crudspring.enums.Category;
import com.tony.crudspring.enums.Status;
import com.tony.crudspring.model.Course;
import com.tony.crudspring.model.CourseWithEnums;
import com.tony.crudspring.repository.CourseRepository;
import com.tony.crudspring.repository.CourseWithEnumsRepository;

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
			   c.setCategory("front-end");
			   courseRepository.save(c);
		};
	}
	@Bean
	CommandLineRunner initDataBaseWithEnums (CourseWithEnumsRepository CourseWithEnumsRepository) {
		return args -> {
			CourseWithEnumsRepository.deleteAll(); // limpando o que existir
			   CourseWithEnums c = new CourseWithEnums();
			   c.setName("Java");
			   c.setCategory(Category.BACKEND);
			 //  c.setStatus(Status.ACTIVE);
			   CourseWithEnumsRepository.save(c);
		};
	}
}
