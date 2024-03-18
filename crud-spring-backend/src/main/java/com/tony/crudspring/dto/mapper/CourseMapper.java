package com.tony.crudspring.dto.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseDTOWithRecord;
import com.tony.crudspring.dto.LessonDTOWithRecord;
import com.tony.crudspring.enums.Category;
import com.tony.crudspring.model.Course;
import com.tony.crudspring.model.Lesson;

@Component
@SuppressWarnings("unchecked")
public class CourseMapper {

    /** Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
    public CourseDTOWithRecord toDTO(Course course) {
        if (course == null) {
            return null;
        }
        List<LessonDTOWithRecord> lessonsDTO = course.getLessons().stream()
                .map(lesson -> new LessonDTOWithRecord(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl())).toList();
               /* .collect(Collectors.toList());*/ 
        return new CourseDTOWithRecord(course.getId(), course.getName(), course.getCategory().getValue(), lessonsDTO);
    }

    /**   List<LessonDTO> lessonDTOList = course.getLessons()
                .stream()
                .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
                .toList(); */

    /** Recebe uma instancia de DTO e converte em uma instacia de Course */
    public Course toEntity(CourseDTOWithRecord courseDTO) {

        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        //course.setStatus("active");
        List<Lesson> lessons = courseDTO.lessons().stream().map(lessonDTO -> {
            /** Lesson */
            var locaLesson = new Lesson(); /** Opcional de não Setar */
            locaLesson.setId(lessonDTO.id());
            locaLesson.setName(lessonDTO.name());
            locaLesson.setYoutubeUrl(lessonDTO.youtubeUrl());
            locaLesson.setCourse(course);
            return locaLesson;
            /**
             * DePara entre Entytes para Hybernete setar o valor,
             * com isto criamos novos cursos e fazemos o Update deles na entidade Lesson
             * course.getLessons().add(locaLesson);
             */
        }).collect(Collectors.toList());
        course.setLessons(lessons);
        return course;
    }

    /** Original Loiane que faz o que o meu ToEnity faz,mas usa o SET */

    public Course toModel(CourseDTOWithRecord courseRequestDTO) {
        Course course = new Course();
        course.setName(courseRequestDTO.name());
        course.setCategory(convertCategoryValue(courseRequestDTO.category()));

        Set<Lesson> lessons = courseRequestDTO.lessons().stream()
                .map(lessonDTO -> {
                    var lesson = new Lesson();
                    if (lesson.getId() > 0) {
                        lesson.setId(lessonDTO.id());
                    }
                    lesson.setName(lessonDTO.name());
                    lesson.setYoutubeUrl(lessonDTO.youtubeUrl());
                    lesson.setCourse(course); /**
                                               * é obrigatorio fz o Depara de uma entidade para outra, caso contrario o
                                               * Hybernete não funciona.
                                               */
                    return lesson;
                }).collect(Collectors.toSet());
        course.setLessons((List<Lesson>) lessons);
        return course;
    }

    /** Metodo Original da Loiane */
    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "back-end" -> Category.BACK_END;
            case "front-end" -> Category.FRONT_END;
            default -> throw new IllegalArgumentException("Invalid Category.");
        };
    }

}
