package com.tony.crudspring.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tony.crudspring.model.CourseWithEnums;

@Repository
public interface CourseWithEnumsRepository extends JpaRepository<CourseWithEnums, Long> {

}
