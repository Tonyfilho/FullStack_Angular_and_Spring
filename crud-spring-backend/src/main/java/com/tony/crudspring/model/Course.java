package com.tony.crudspring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;



@Data // Pertence ao lombok, e ja gera Get, Set ToString e RashCode
@Entity // vai ser uma tabela na DB do JPA
public class Course {
    
    @Id //diz a JPA que isto é um chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @ Column(length = 200, nullable =  false)
    private String name;

    @ Column(length = 10, nullable =  false)
    private String category;
}
