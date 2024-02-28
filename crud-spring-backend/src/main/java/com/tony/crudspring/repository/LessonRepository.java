package com.tony.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.crudspring.model.Lesson;

@Repository // extends o Spring data jpa
public interface LessonRepository extends JpaRepository<Lesson, Long>{

    

    
}
