package com.tony.crudspring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;

import com.tony.crudspring.dto.CourseDTOWithRecord;
import com.tony.crudspring.dto.LessonDTOWithRecord;
import com.tony.crudspring.dto.mapper.CourseMapper;
import com.tony.crudspring.exception.RecordNotFoundException;
import com.tony.crudspring.model.Course;
import com.tony.crudspring.model.Lesson;
import com.tony.crudspring.repository.CourseRepository;
import com.tony.crudspring.repository.LessonRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Service
@Validated
@SuppressWarnings("null")
public class CourseServiceWithRecordAndExeption {
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final LessonRepository lessonRepository;

    /*************** Service com exeption personalizadas **************/
    public CourseServiceWithRecordAndExeption(
            CourseRepository courseRepository,
            CourseMapper courseMapper,
            LessonRepository lessonRepository) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.lessonRepository = lessonRepository;

    }

    /** Conversão de COURSE para o DTO CourseDTOWithRecord, forma tradicional */
    public List<CourseDTOWithRecord> listConversaoPadrao() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDTOWithRecord> dtosRecords = new ArrayList<>(courses.size());
        List<Lesson> lessons = lessonRepository.findAll();
        List<LessonDTOWithRecord> lessonDTO = new ArrayList<>(lessons.size());
        for (Course course : courses) {
            for (Lesson courseLesson : course.getLessons()) {
                lessonDTO.add(new LessonDTOWithRecord(courseLesson.getId(), courseLesson.getName(),
                        courseLesson.getYoutubeUrl()));
            }
            CourseDTOWithRecord localDtoWithRecord = new CourseDTOWithRecord(course.getId(), course.getName(),
                    course.getStatus(), lessonDTO);
            dtosRecords.add(localDtoWithRecord);
        }
        return dtosRecords;
    }

    public List<CourseDTOWithRecord> list() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toDTO)
                // .collect(ArrayList:: new, ArrayList:: add, ArrayList :: addAll); //ou assim
                .collect(Collectors.toList());
    }

    public CourseDTOWithRecord create(@Valid @NotNull CourseDTOWithRecord courseDTOWithRecord) {
        return this.courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(courseDTOWithRecord)));

    }

    public CourseDTOWithRecord updateWithExeption(@NotNull @Positive Long id,
            @Valid CourseDTOWithRecord courseDTO) {
        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(courseDTO.name());
            recordFound.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
            /**
             * Para não dar o error de cascade do Hibernete, por termos referencias na
             * memorias diferentes
             * 1º q tem uma lista vinda do recordFound
             * 2º q vem do Update quando atualizamos os dados, temos que ter esta
             * abordagem
             * de limpar a referencia de memoria e voltar atualiza-la com a lista que
             * vem do front
             */
            recordFound.getLessons().clear(); /** Lista de cursos da DB */
            Course localCourseFromFrontCourse = courseMapper.toEntity(courseDTO); /**
                                                                    * Todas cursos e liçoes que vem do DTO ou seja front
                                                                    */
            /*
             * / localCourseFromFrontCourse.getLessons().forEach(lesson ->
             * recordFound.getLessons().add(lesson)); ou assim usando Method reference
             */
            localCourseFromFrontCourse.getLessons().forEach(recordFound.getLessons()::add); /**Pegando cada item q vem do DTO e adcionando na DB */
            return courseMapper.toDTO(courseRepository.save(recordFound));

        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTOWithRecord findByIdWithExeption(@PathVariable("id") @NotNull @Positive Long id) {
        /**
         * return courseRepository.findById(id).orElseThrow(() -> new
         * RecordNotFoundException(id)); // SEM DTO
         * return courseRepository.findById(id).map(courseMapper ::
         * toDTO).orElseThrow(() -> new RecordNotFoundException(id)); // COM LAMBDA
         * CourseDTOWithRecord courceDTO =
         * courseRepository.findById(id).map(courseMapper:: toDTO);
         * 
         */
        return courseRepository.findById(id).map(recordFoundCourse -> {
            /**
             * ou usa o Set Local ou Mapper
             * Course localCourse = new Course();
             * localCourse.setId(course.getId());
             * localCourse.setName(course.getName());
             * localCourse.setCategory(course.getCategory());
             * localCourse.setLessons(course.getLessons());
             */
            return courseMapper.toDTO(recordFoundCourse);
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteWithExeption(@NotNull @Positive Long id) {
        courseRepository.findById(id).map(recordFoundCourse -> {
            courseRepository.deleteById(id);
            return true;
        }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void deleteWithExeptionDeleteEntyte(@NotNull @Positive Long id) {
        /**
         * èxecultado isto: courseRepository.delete(courseRepository.findById(id), caso
         * não ache ele execulta isto: orElseThrow(() -> new
         * RecordNotFoundException(id))
         */
        /**
         * quando busco o findById ele retorna uma entidade,e com isto consiguiremos
         * deletar a entidade
         */
        Course orElseThrow = courseRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id));
        courseRepository.delete(orElseThrow);
    }

}
