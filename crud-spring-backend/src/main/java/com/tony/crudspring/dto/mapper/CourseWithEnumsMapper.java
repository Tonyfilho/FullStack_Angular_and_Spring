package com.tony.crudspring.dto.mapper;

import org.springframework.stereotype.Component;

import com.tony.crudspring.dto.CourseWithEnumsDTO;
import com.tony.crudspring.enums.Category;
import com.tony.crudspring.model.CourseWithEnums;

@Component
@SuppressWarnings("static-access")
public class CourseWithEnumsMapper {

    /** Recebe uma instacia da Entidade Course e retorna uma instancia do DTO */
   
    public CourseWithEnumsDTO toDTO(CourseWithEnums course) {
        Category locaCategory = course.getCategory();

        if (course.getCategory().toString().equals("BACKEND")) {
            return new CourseWithEnumsDTO(course.getId(), course.getName(),
                    locaCategory.BACK_END, course.getLessons());
        }
        return new CourseWithEnumsDTO(course.getId(), course.getName(),
                locaCategory.FRONT_END, course.getLessons());
    }

    /** Recebe uma instancia de DTO e converte em uma instacia de Course */
    public CourseWithEnums toCourse(CourseWithEnumsDTO courseWithEnumsDTO) {
        CourseWithEnums localCourse = new CourseWithEnums();    
        // if (courseWithEnumsDTO == null) {
        // return null;
        // }
        if (courseWithEnumsDTO.id() != null) {
            localCourse.setId(courseWithEnumsDTO.id());
        }
        localCourse.setName(courseWithEnumsDTO.name());
        if (courseWithEnumsDTO.category().toString().equals("BACKEND")) {
            localCourse.setCategory(Category.BACK_END);
            // n podemos setar o active ja é setado na entidade
            return localCourse;
        }
        localCourse.setCategory(Category.FRONT_END);
      //  localCourse.setCategory(convertersCategoryValue(courseWithEnumsDTO.category().toString()) );
        return localCourse;
    }


    /**Um outro codigo que podemos fazer com a nova feature do java 14, não precisamos usar os IFs dentro do ToCourse */
    /**Usaremos uma Expressão Switch */
    /**Usaremos uma lambda para retorna oq ue queremos, temos que retornar ou por blocos { return Category.BACKEND; }     */
    public Category convertersCategoryValue(String value) {       
        if (value == null) {
            return null;
        }
     return switch (value) {
            case "FRONTEND" ->  Category.FRONT_END;         
            case "BACKEND"  ->   Category.BACK_END;               
            default -> throw new IllegalArgumentException("Invalid Value: "+ value) ; 
            //  default  IllegalArgumentException :: throw ;  // tem q acertar
        
         
        };

    }

}
