package com.tony.crudspring.dto;

import com.tony.crudspring.model.Course;

public record LessonDTOWithRecord(Long id, String name, String youtubeUrl, Course course) {

}
