package com.tony.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseWithEnumsDTO;
import com.tony.crudspring.model.CourseWithEnums;

@Component
public class CourseWithEnumsMapper {

    /** Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
    public CourseWithEnumsDTO toDTO(CourseWithEnums courseWithEnums) {
        if (courseWithEnums == null) {
            return null;
        }
        if (courseWithEnums.getCategory().equals("BACKEND")) {
            return new CourseWithEnumsDTO(courseWithEnums.getId(), courseWithEnums.getName(),
                    courseWithEnums.getCategory().BACKEND);

        }
        return new CourseWithEnumsDTO(courseWithEnums.getId(), courseWithEnums.getName(),
                courseWithEnums.getCategory().FRONTEND);
    }

    /** Recebe uma instancia de DTO e converte em uma instacia de Course */
    public CourseWithEnums toCourse(CourseWithEnumsDTO courseWithEnumsDTO) {
        CourseWithEnums localCourse = new CourseWithEnums();
        if (courseWithEnumsDTO == null) {
            return null;
        }
        if (courseWithEnumsDTO.id() != null) {
            localCourse.setId(courseWithEnumsDTO.id());
        }
        localCourse.setName(courseWithEnumsDTO.name());
        if (courseWithEnumsDTO.category().equals("BACKEND")) {
            localCourse.setCategory(courseWithEnumsDTO.category().BACKEND);
            localCourse.setStatus("active");
            return localCourse;
        }
        localCourse.setCategory(courseWithEnumsDTO.category().FRONTEND);
        localCourse.setStatus("active");
        return localCourse;
    }

}
