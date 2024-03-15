package com.tony.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LessonDTOWithRecord(
        Long id,
        @NotNull @NotBlank @Length(min = 2, max = 100) String name,
        @NotNull @NotBlank @Length(min = 10, max = 40) String youtubeUrl) {

}
