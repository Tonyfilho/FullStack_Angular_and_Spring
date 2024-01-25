package com.tony.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseDTOWithRecord;
import com.tony.crudspring.model.Course;

@Component
public class CourseMapper {

    /**Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
    public CourseDTOWithRecord toDTO(Course course) {
        return new CourseDTOWithRecord(course.getId(), course.getName(), course.getCategory());
    }




}
