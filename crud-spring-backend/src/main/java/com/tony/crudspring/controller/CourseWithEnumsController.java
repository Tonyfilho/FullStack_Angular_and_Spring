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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tony.crudspring.dto.CourseWithEnumsDTO;
import com.tony.crudspring.service.CourseWithEnumsService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping("/api/coursewithenums")
public class CourseWithEnumsController {

    private final CourseWithEnumsService courseWithEnumsService;

    /** Criamos o construtor ao invez de usar o lombok */
    public CourseWithEnumsController(CourseWithEnumsService courseWithEnumsService) {

        this.courseWithEnumsService = courseWithEnumsService;
    }


    @GetMapping
    public List<CourseWithEnumsDTO> list() {
        return this.courseWithEnumsService.list();
    }

    @GetMapping("/{id}")
    public CourseWithEnumsDTO findByIdExeptions(@PathVariable("id") @NotNull @Positive Long id) {
        return this.courseWithEnumsService.findByIdWith(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CourseWithEnumsDTO> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseWithEnumsDTO course) {
        CourseWithEnumsDTO localDto = this.courseWithEnumsService.update(id, course);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(localDto) ;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        this.courseWithEnumsService.deleteWithExeption(id);
    }

      
    @PostMapping
    public ResponseEntity<CourseWithEnumsDTO> create(@RequestBody @Valid CourseWithEnumsDTO courseWithenumsDTO) {
        CourseWithEnumsDTO localDto = this.courseWithEnumsService.create(courseWithenumsDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(localDto);
    }



}
