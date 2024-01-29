package com.tony.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseWithEnumsDTO;
import com.tony.crudspring.enums.Category;
import com.tony.crudspring.model.CourseWithEnums;

@Component
public class CourseWithEnumsMapper {

    /** Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
    public CourseWithEnumsDTO toDTO(CourseWithEnums courseWithEnums) {
        Category locaCategory = courseWithEnums.getCategory();

        if (courseWithEnums.getCategory().toString().equals("BACKEND")) {
            return new CourseWithEnumsDTO(courseWithEnums.getId(), courseWithEnums.getName(),
                    locaCategory.BACKEND);
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
        if (courseWithEnumsDTO.category().toString().equals("BACKEND")) {
            localCourse.setCategory(courseWithEnumsDTO.category().BACKEND);
            localCourse.setStatus("active");
            return localCourse;
        }
        localCourse.setCategory(courseWithEnumsDTO.category().FRONTEND);
        localCourse.setStatus("active");
        return localCourse;
    }

}
