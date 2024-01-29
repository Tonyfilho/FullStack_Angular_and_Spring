package com.tony.crudspring.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.tony.crudspring.dto.CourseWithEnumsDTO;
import com.tony.crudspring.dto.mapper.CourseWithEnumsMapper;
import com.tony.crudspring.exception.RecordNotFoundException;
import com.tony.crudspring.model.CourseWithEnums;
import com.tony.crudspring.repository.CourseWithEnumsRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
public class CourseWithEnumsService {

    private final CourseWithEnumsRepository courseWithEnumsRepository;
    private final CourseWithEnumsMapper courseWithEnumsMapper;

    /*************** Service com exeption personalizadas **************/
    public CourseWithEnumsService(CourseWithEnumsRepository courseWithEnumsRepository,
            CourseWithEnumsMapper courseWithEnumsMapper) {
        this.courseWithEnumsRepository = courseWithEnumsRepository;
        this.courseWithEnumsMapper = courseWithEnumsMapper;

    }


   /**Conversão de COURSE para o DTO CourseDTOWithRecord*/

    public List<CourseWithEnumsDTO> list() {
        return courseWithEnumsRepository.findAll()
        .stream()
        .map(courseWithEnumsMapper:: toDTO)
        //.collect(ArrayList:: new, ArrayList:: add, ArrayList :: addAll); //ou assim
        .collect(Collectors.toList());
    }

    public CourseWithEnumsDTO create(@Valid @NotNull CourseWithEnumsDTO courseWithEnumsDTO ) {
       // CourseWithEnumsDTO localWithEnumsDTO = 
        return this.courseWithEnumsMapper.toDTO(courseWithEnumsRepository.save(courseWithEnumsMapper.toCourse(courseWithEnumsDTO)));

    }

    public CourseWithEnumsDTO findByIdWith(@PathVariable("id") @NotNull @Positive Long id) {      
       return courseWithEnumsRepository.findById(id)
       .map(courseMapper -> new CourseWithEnumsDTO(courseMapper.getId(), courseMapper.getName(), courseMapper.getCategory()))
       .orElseThrow(() -> new RecordNotFoundException(id)); 
    }


   public CourseWithEnumsDTO update(@NotNull @Positive Long id, @Valid CourseWithEnumsDTO courseWithEnumsDTO) {
        return courseWithEnumsRepository.findById(id).map(recordFound -> {
            recordFound.setName(courseWithEnumsDTO.name());
            recordFound.setCategory(courseWithEnumsDTO.category());

            // return this.courseWithEnumsMapper.toDTO(recordFound );
            courseWithEnumsRepository.save(recordFound);
         return  this.courseWithEnumsMapper.toDTO(recordFound );

            // if (courseWithEnumsDTO.category().equals("BACKEND")) {
            //     recordFound.setCategory(courseWithEnumsDTO.category().BACKEND);
            // return this.courseWithEnumsMapper.toDTO(courseWithEnumsRepository.save(recordFound));
            // }
            // recordFound.setCategory(courseWithEnumsDTO.category().FRONTEND);
            // return this.courseWithEnumsMapper.toDTO(courseWithEnumsRepository.save(recordFound));

        }).orElseThrow(() ->new  RecordNotFoundException(id));
    }

    public void deleteWithExeption(@NotNull @Positive Long id) {
        courseWithEnumsRepository.findById(id).map(result -> {
            courseWithEnumsRepository.deleteById(id);
            return true;
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }
    
    public void deleteWithExeptionDeleteEntyte(@NotNull @Positive Long id) {
        CourseWithEnums locaCourseWithEnums = courseWithEnumsRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id) );
        courseWithEnumsRepository.delete(locaCourseWithEnums);
    }







}
