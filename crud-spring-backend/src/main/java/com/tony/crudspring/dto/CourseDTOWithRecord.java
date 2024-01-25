package com.tony.crudspring.dto;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
/**
 * Manteremos somente as validações e removeremos tudo que for para JPA e
 * Hibernate ligados ao DB
 */
/** Este padrão de DTO Após o java14 temos um recurso chamado RECORDs */
/**
 * Record é um tipo de class sem somente com os GETS e com o CONSTRUTOR completo
 * com todos os atributos
 */
/** Não se consegue mudar as informações,a RECORD é uma class IMUTAVEL */
/**E não é usado mais a palavra GET, se aplica diretamente o nome das PROPRIEDADES */
/**A Record Não extend class, mas pode implementar interfaces */

public record CourseDTOWithRecord(
    @JsonProperty("_id") Long id, 
    @NotBlank @NotNull @Length(min = 2, max = 100) String name, 
    @NotNull @Length(max = 10) @Pattern(regexp = "back-end|front-end") String category
    ) {


    
}
