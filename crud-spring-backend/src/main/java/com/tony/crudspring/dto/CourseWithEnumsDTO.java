package com.tony.crudspring.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tony.crudspring.enums.Category;
import com.tony.crudspring.model.LessonWithEnums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseWithEnumsDTO( 
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 2, max = 100) String name, 
    @Enumerated(EnumType.STRING) Category category, List<LessonWithEnums> lessons) {
    
}
