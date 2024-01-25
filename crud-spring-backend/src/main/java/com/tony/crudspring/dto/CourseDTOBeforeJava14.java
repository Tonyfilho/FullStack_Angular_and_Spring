package com.tony.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data //@ata vem do lombok e gera todos o Gets e Sets construtors, serialize e eguals
public class CourseDTOBeforeJava14 {
    
    /**Manteremos somente as validações e removeremos tudo que for para JPA e Hibernate ligados ao DB */
    /**Este  padrão de DTO é usando para Java abaixo do 14, OPos o java 14 temos um recurso chamado RECORDs */

    
    @JsonProperty("_id")
    private Long id;

    @Length(min=2, max=100)
    @NotNull
    @NotBlank   
    private String name;

    @Length(max=100)
    @NotNull
    @NotBlank
    @Pattern(regexp ="back-end|front-end")
    private String category;



    @Length(max=8)
    @NotNull
    @NotBlank
    @Pattern(regexp ="active|inactive")
    private String status = "active";
}
