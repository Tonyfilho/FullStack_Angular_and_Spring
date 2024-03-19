package com.tony.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

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
	@Profile("dev") /**Temos que especificar  o profile, q neste caso será o DEv do application-dev */
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
		 for(int i = 0; i< 20; i++) {
					Course c = new Course();
					c.setName("Angular Com Spring" +i);
					c.setCategory(Category.FRONT_END);			
					
					Lesson locaLesson = new Lesson();
					locaLesson.setName("Introduction");
					locaLesson.setYoutubeUrl("vRf1-Z4_7vI?si=ms6gI1ymDnpcOm");
					locaLesson.setCourse(c); /** setando o Objeto Course dentro da Entidade Lesson */
					c.getLessons().add(locaLesson);
					//courseRepository.save(c);

					Lesson l2 = new Lesson();
					l2.setName("Angular");
					l2.setYoutubeUrl("vRf1-Z4_7vI?si=ms6gI1ymDnpcOm");
					l2.setCourse(c); /** setando o Objeto Course dentro da Entidade Lesson */
					c.getLessons().add(l2);
					courseRepository.save(c);
			 };

		 };
		 
	}

	// @Bean
	// CommandLineRunner initDataBaseWithEnums(CourseWithEnumsRepository CourseWithEnumsRepository) {
	// 	return args -> {
	// 		CourseWithEnumsRepository.deleteAll(); // limpando o que existir
	// 		CourseWithEnums localCourse = new CourseWithEnums();
	// 		LessonWithEnums locaLesson = new LessonWithEnums();
	// 		localCourse.setName("Java");
	// 		localCourse.setCategory(Category.BACK_END);
	// 		locaLesson.setName("Introduction");
	// 		locaLesson.setYoutubeUrl("vRf1-Z4_7vI?si=ms6gI1ymDnpcOmKp");
	// 		// localCourse.getLessons().add(locaLesson);
	// 		CourseWithEnumsRepository.save(localCourse);
	// 	};
	// }
}
