package com.tony.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tony.crudspring.enums.Category;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CourseWithEnumsDTO( 
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 2, max = 100) String name, 
    @NotNull @Length(max = 10) @Enumerated(EnumType.STRING) Category category) {
    
}
