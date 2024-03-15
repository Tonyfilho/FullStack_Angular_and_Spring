package com.tony.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tony.crudspring.enums.Category;
import com.tony.crudspring.model.Course;
import com.tony.crudspring.model.CourseWithEnums;
import com.tony.crudspring.model.Lesson;
import com.tony.crudspring.model.LessonWithEnums;
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
		/**
		 * Obs: Quando temos relacionamento entre ENTYTES
		 * Lembrando que estes Objetos são REFERENCIA de Memoria
		 * 1º Sempre gerar o Objeto Principal, neste caso localCourse apara termos o ID de Course;
		 * 2º Gerar o Objeto secundario e Setar o Objeto Principal dentro do Secundario locaLesson.setCourse(localCourse);
		 * Setando desta forma mandaremos o ID da Entidade Course para Entidade Lesson e o Hibernate ou JPA faz o restante 
		 * Fazendo o INSERT na tabela com as informações
		 * 3ª Adcionar na List de Lesson no Objeto Primario
		 * 4ª Salvar na DB
		 * 
		 */
		return args -> {
               courseRepository.deleteAll(); // limpando o que existir
			   Course localCourse = new Course();
			   localCourse.setName("Angular Com Spring");
			   localCourse.setCategory(Category.FRONT_END);

			   Lesson locaLesson = new Lesson();
			   locaLesson.setName("Introduction");
			   locaLesson.setYoutubeUrl("vRf1-Z4_7vI?si=ms6gI1ymDnpcOmKp");
			   locaLesson.setCourse(localCourse); /** setando o Objeto Course dentro da Entidade Lesson */
			   localCourse.getLessons().add(locaLesson);
			   courseRepository.save(localCourse);

			   Lesson locaLesson2 = new Lesson();
			   locaLesson2.setName("Angular");
			   locaLesson2.setYoutubeUrl("vRf1-Z4_7vI?si=ms6gI1ymDnpcOmKp");
			   locaLesson2.setCourse(localCourse); /** setando o Objeto Course dentro da Entidade Lesson */
			   localCourse.getLessons().add(locaLesson2);
			   courseRepository.save(localCourse);
		};
	}
	@Bean
	CommandLineRunner initDataBaseWithEnums (CourseWithEnumsRepository CourseWithEnumsRepository) {
		return args -> {
			CourseWithEnumsRepository.deleteAll(); // limpando o que existir
			   CourseWithEnums localCourse = new CourseWithEnums();
			   LessonWithEnums locaLesson = new LessonWithEnums();
			   localCourse.setName("Java");
			   localCourse.setCategory(Category.BACK_END);
			   locaLesson.setName("Introduction");
			   locaLesson.setYoutubeUrl("vRf1-Z4_7vI?si=ms6gI1ymDnpcOmKp");
			 //  localCourse.getLessons().add(locaLesson);
			   CourseWithEnumsRepository.save(localCourse);
		};
	}
}
