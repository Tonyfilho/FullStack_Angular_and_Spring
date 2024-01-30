package com.tony.crudspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data // lombok
public class Lesson {
    @Id // diz a JPA que isto é um chave primaria
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String youtubeUrl;
    /** So salvaremos o final do link */

    /**
     * Mapeando o Lesson com A entidade Course OneToMany e aqui em Lesson ManyToOne
     * Temos a opção FetchType.LAZY q so carregará este mapeamento quando  usarmos o GetLesson();
     * optional = false que dizer q esta coluna tem que ser obrigatoria quando houver um GetLesson();
     * @JoinColumn tem que sair de Course e vir para aqui no ManyToOne para dar nome a coluna
     */
    @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}
