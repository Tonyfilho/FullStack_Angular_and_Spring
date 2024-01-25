package com.tony.crudspring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.tony.crudspring.exception.RecordNotFoundException;
import com.tony.crudspring.model.Course;
import com.tony.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseServiceWithExeption {
    private final CourseRepository courseRepository;

    /*************** Service com exeption personalizadas **************/
    public CourseServiceWithExeption(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);

    }

    public Course findByIdWithExeption(@PathVariable("id") @NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }


   public Course updateWithExeption(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            return courseRepository.save(recordFound);
        }).orElseThrow(() ->new  RecordNotFoundException(id));
    }

    public void deleteWithExeption(@NotNull @Positive Long id) {
        courseRepository.findById(id).map(result -> {
            courseRepository.deleteById(id);
            return true;
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    public void deleteWithExeptionDeleteEntyte(@NotNull @Positive Long id) {
        /** èxecultado isto: courseRepository.delete(courseRepository.findById(id), caso não ache ele execulta isto: orElseThrow(() -> new RecordNotFoundException(id)) */
        /**quando busco o findById ele retorna uma entidade,e com isto consiguiremos deletar a entidade */
        Course orElseThrow = courseRepository.findById(id)
        .orElseThrow(() -> new RecordNotFoundException(id));
        courseRepository.delete(orElseThrow);
    }



}
