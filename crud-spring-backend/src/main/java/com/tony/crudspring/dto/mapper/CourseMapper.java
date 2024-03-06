package com.tony.crudspring.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseDTOWithRecord;
import com.tony.crudspring.dto.LessonDTOWithRecord;
import com.tony.crudspring.model.Course;
import com.tony.crudspring.model.Lesson;

@Component
public class CourseMapper {

    /** Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
    public CourseDTOWithRecord toDTO(Course course) {
        if (course == null) {
            return null;
        }
       List<LessonDTOWithRecord> lessonsDTO = course.getLessons().stream().map(lesson -> new LessonDTOWithRecord(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
        .collect(Collectors.toList());
        return new CourseDTOWithRecord(course.getId(), course.getName(), course.getCategory(), lessonsDTO);
    }

    /** Recebe uma instancia de DTO e converte em uma instacia de Course */
    public Course toEntity(CourseDTOWithRecord courseDTOWithRecord) {
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
