package com.tony.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseDTOWithRecord;
import com.tony.crudspring.model.Course;
import com.tony.crudspring.model.Lesson;

@Component
public class CourseMapper {

    /** Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
    public CourseDTOWithRecord toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTOWithRecord(course.getId(), course.getName(), course.getCategory(), course.getLessons());
    }

    /** Recebe uma instancia de DTO e converte em uma instacia de Course */
    public Course toCourse(CourseDTOWithRecord courseDTOWithRecord) {
        Course localCourse = new Course();
        Lesson locaLesson = new Lesson();
        if (courseDTOWithRecord == null) {
            return null;
        }
        if (courseDTOWithRecord.id() != null) {
            localCourse.setId(courseDTOWithRecord.id());
        }
        localCourse.setName(courseDTOWithRecord.name());
        localCourse.setCategory(courseDTOWithRecord.category());
        localCourse.setStatus("active");
        /**Lesson */
        locaLesson.setId(courseDTOWithRecord.id());
        locaLesson.setName(courseDTOWithRecord.name());
        locaLesson.setYoutubeUrl(locaLesson.getYoutubeUrl());
        localCourse.getLessons().add(locaLesson);
        return localCourse;
    }
}
