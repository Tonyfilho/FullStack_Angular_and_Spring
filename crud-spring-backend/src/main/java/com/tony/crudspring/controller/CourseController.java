package com.tony.crudspring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestMethod;

import com.tony.crudspring.model.Course;
import com.tony.crudspring.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

//Obs: @Valid serve para as validações das ENTIDADES e não as Locais.
@Validated // @Validated serve p as validações Locais Ex: locais @NotNull @Positive funcione somente nos parametros dos mehodos e não as da entidade
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> findById(@PathVariable("id") @NotNull @Positive Long id) {
        return courseRepository.findById(id).map(recordFound -> ResponseEntity.ok().body(recordFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid Course course) {
        courseRepository.save(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course) {

        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            Course updateCourse = courseRepository.save(recordFound);
            return ResponseEntity.ok().body(updateCourse);
        })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> delete(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().<Course>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** ex: de retornos */
    public ResponseEntity<Object> delete2(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** ex: de retornos */
    public ResponseEntity<?> delete3(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** ex: de retornos */
    public ResponseEntity<Void> delete4(@PathVariable @NotNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(recordFound -> {
                    courseRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
