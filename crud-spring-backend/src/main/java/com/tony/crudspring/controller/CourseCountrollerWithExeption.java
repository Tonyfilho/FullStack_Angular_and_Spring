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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tony.crudspring.dto.CourseDTOWithRecord;
import com.tony.crudspring.dto.CoursePageDTO;
import com.tony.crudspring.service.CourseServiceWithRecordAndExeption;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.websocket.server.PathParam;

@Validated // @Validated serve p as validações Locais Ex: locais @NotNull @Positive
           // funcione somente nos parametros dos mehodos e não as da entidade
@RestController
@RequestMapping("/api/coursesexeption")
public class CourseCountrollerWithExeption {

    private final CourseServiceWithRecordAndExeption courseServiceWithExeption;

    /** Criamos o construtor ao invez de usar o lombok */
    public CourseCountrollerWithExeption(CourseServiceWithRecordAndExeption courseServiceWithExeption) {

        this.courseServiceWithExeption = courseServiceWithExeption;
    }

    /****** Class with Exeptions **** */

    @GetMapping
    public List<CourseDTOWithRecord> list() {
        return courseServiceWithExeption.list();
    }

    @GetMapping("/paginator")
    public CoursePageDTO listAllWithPage(@PathParam("/paginator") @PositiveOrZero @RequestParam(defaultValue="0") int page,
     @Positive @Max(100) @RequestParam(defaultValue="10") int pageSize) {
        return courseServiceWithExeption.listAllWithPage(page, pageSize);
    }

    @GetMapping("/{id}")
    public CourseDTOWithRecord findByIdExeptions(@PathVariable("id") @NotNull @Positive Long id) {
        return courseServiceWithExeption.findByIdWithExeption(id);
    }

    @PutMapping("/{id}")
    public CourseDTOWithRecord update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid CourseDTOWithRecord courseDTO) {
        return courseServiceWithExeption.updateWithExeption(id, courseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id) {
        courseServiceWithExeption.deleteWithExeption(id);
    }

      
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<CourseDTOWithRecord> create(@RequestBody @Valid CourseDTOWithRecord course) {
        CourseDTOWithRecord localDto =  courseServiceWithExeption.create(course);
        return ResponseEntity.status(HttpStatus.CREATED).body(localDto);
    }

}
