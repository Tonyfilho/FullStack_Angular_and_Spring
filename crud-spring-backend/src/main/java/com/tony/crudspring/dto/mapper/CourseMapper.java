package com.tony.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseDTOWithRecord;
import com.tony.crudspring.model.Course;

@Component
public class CourseMapper {

    /** Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
    public CourseDTOWithRecord toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTOWithRecord(course.getId(), course.getName(), course.getCategory());
    }

    /** Recebe uma instancia de DTO e converte em uma instacia de Course */
    public Course toCourse(CourseDTOWithRecord courseDTOWithRecord) {
        Course localCourse = new Course();
        if (courseDTOWithRecord == null) {
            return null;
        }
        if (courseDTOWithRecord.id() != null) {
            localCourse.setId(courseDTOWithRecord.id());
        }
        localCourse.setName(courseDTOWithRecord.name());
        localCourse.setCategory(courseDTOWithRecord.category());
        localCourse.setStatus("active");
        return localCourse;
    }
}
