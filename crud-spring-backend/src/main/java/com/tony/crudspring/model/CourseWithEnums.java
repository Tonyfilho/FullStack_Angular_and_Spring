package com.tony.crudspring.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tony.crudspring.enums.Category;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data // Pertence ao lombok, e ja gera Get, Set ToString e RashCode
@Entity // vai ser uma tabela na DB do JPA
@SQLDelete(sql = "UPDATE COURSE_WITH_ENUMS  SET status = 'inactive' WHERE status = 'active' and id = ?") // usando o Where
public class CourseWithEnums {

    @Id // diz a JPA que isto é um chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @Length(min = 2, max = 100)
    @NotNull
    @NotBlank
    @Column(length = 100, nullable = false)
    private String name;

    // @Length(max = 20)
    // @Pattern(regexp = "BACKEND|FRONTEND")
    @NotNull
   // @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Length(max = 8)
    @NotNull
    @NotBlank
    @Pattern(regexp = "active|inactive")
    @Column(length = 10, nullable = false)
    private String status = "active";

}
