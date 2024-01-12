package com.tony.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.crudspring.model.Course;

@Repository // extends o Spring data jpa
public interface CourseRepository extends JpaRepository<Course, Long> {

}
