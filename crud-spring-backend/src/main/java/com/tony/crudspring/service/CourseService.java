package com.tony.crudspring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.tony.crudspring.model.Course;
import com.tony.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

//Component podemos por @Component o @Service é uma convensão
@Service
@Validated
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    @SuppressWarnings("null")
    public Optional<Course> findById(@PathVariable("id") @NotNull @Positive Long id) {
        return courseRepository.findById(id);
    }
 

    @SuppressWarnings("null")
    public Course create(@Valid Course course) {
        return courseRepository.save(course);

    }
    @SuppressWarnings("null")
    public Optional<Course> update(@NotNull @Positive Long id, @Valid Course course) {

        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            return courseRepository.save(recordFound);
        });
    }

    @SuppressWarnings("null")
    public boolean delete(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(result -> {
            courseRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @SuppressWarnings("null")
    public Optional<Course> delete2(@NotNull @Positive Long id) {
        return courseRepository.findById(id);
    }

    
}
