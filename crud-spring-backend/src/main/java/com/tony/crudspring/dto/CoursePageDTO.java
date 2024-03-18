package com.tony.crudspring.dto;

import java.util.List;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

/**Criamos um DTO para o Page que recebe uma List q é outro DTO */
public record CoursePageDTO(List<CourseDTOWithRecord> courses, 
@PositiveOrZero long totalElments, @Positive @Max(100) int totalPages) {
    
}
